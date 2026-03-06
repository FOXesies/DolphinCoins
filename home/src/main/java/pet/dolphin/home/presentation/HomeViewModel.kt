package pet.dolphin.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import pet.dolphin.core.domain.util.onError
import pet.dolphin.core.domain.util.onSuccess
import pet.dolphin.home.data.mappers.toBinanceSymbol
import pet.dolphin.home.data.mappers.toCoinsInfo
import pet.dolphin.home.data.mappers.toPercentInfo
import pet.dolphin.home.data.mappers.toUi
import pet.dolphin.home.domain.model.FundPreview
import pet.dolphin.home.domain.usecase.GetTopPopularFundsUseCase
import pet.dolphin.home.domain.usecase.ObserveTopFundsUseCase
import pet.dolphin.home.presentation.balance.model.BalanceUI
import pet.dolphin.home.presentation.model.DisplayableNumber
import pet.dolphin.home.presentation.model.Effect
import pet.dolphin.home.presentation.model.HomeAction
import pet.dolphin.home.presentation.model.HomeEvent
import pet.dolphin.home.presentation.model.HomeScreenState
import kotlin.collections.forEach

class HomeViewModel (
    private val getTopPopularFundsUseCase: GetTopPopularFundsUseCase,
    private val observeTopFundsUseCase: ObserveTopFundsUseCase
): ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state: StateFlow<HomeScreenState> = _state.asStateFlow()
        .onStart { loadFundsInfo() }
        .stateIn(viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            HomeScreenState()
        )

    private val _events = Channel<HomeEvent>()
    val events = _events.receiveAsFlow()

    private val _effect = Channel<Effect>()
    val effect = _effect.receiveAsFlow()

    fun onAction(action: HomeAction){
        when(action){
            HomeAction.LoadData, HomeAction.Retry -> loadFundsInfo()
            is HomeAction.OnTapFund -> sendEffect(Effect.NavigateDetail(action.id))
            HomeAction.OnTapProfile -> TODO()
        }
    }

    private fun sendEffect(effect: Effect) {
        viewModelScope.launch { _effect.send(effect) }
    }

    private fun loadFundsInfo(){
        println("loadFundsInfo: Start")

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            supervisorScope {
                async(Dispatchers.IO) {
                    getTopPopularFundsUseCase
                        .invoke()
                        .onSuccess { result ->
                            val mapFunds = result.associate { fund ->
                                val ui = fund.toUi()
                                fund.symbol.toBinanceSymbol() to ui
                            }

                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    topPopularFunds = mapFunds,
                                    myFunds = mapFunds.mapValues {
                                        it.value.copy(
                                            changeCurrency = (it.value.changeCurrency?.value?.times(2))?.toCoinsInfo(),
                                            totalCoinsPrice = (it.value.totalCoinsPrice.value * 2).toCoinsInfo()
                                        )
                                    },
                                    myBalance = BalanceUI(
                                        id = "1",
                                        totalCoinsPrice = it.myFunds.values.sumOf { fund -> fund.totalCoinsPrice.value }.toCoinsInfo(),
                                        changePercent24Hr = DisplayableNumber(
                                            value = -2501.7,
                                            formatted = "-7.7%"
                                        ),
                                        changeCurrency24Hr = it.myFunds.values.sumOf { fund ->
                                            (fund.changeCurrency?.value ?: 0).toDouble()
                                        }.toCoinsInfo()
                                    )
                                )
                            }

                            observeTopFunds(mapFunds.keys)
                        }
                        .onError { errorMesssage ->
                            _state.update { it.copy(isLoading = false) }

                            //ADD MAPPER ERROR
                            _events.send(HomeEvent.Error(errorMesssage.toString()))
                        }
                    // fun get My Funds
                }
            }
        }
    }

    private suspend fun observeTopFunds(fundsSymbols: Set<String>) {
        observeTopFundsUseCase(fundsSymbols).onEach { fund ->
            _state.update { curState ->
                val updated = curState.topPopularFunds.toMutableMap()
                val current = updated[fund.symbol]

                val muFunds = updated.mapValues {
                    it.value.copy(
                        changeCurrency = (it.value.changeCurrency?.value?.times(2))?.toCoinsInfo(),
                        totalCoinsPrice = (it.value.totalCoinsPrice.value * 2).toCoinsInfo()
                    )
                }

                if (current != null) {
                    updated[fund.symbol] = current.copy(
                        totalCoinsPrice = fund.currentPrice.toCoinsInfo(),
                        changePercent = fund.priceChangePercent.toPercentInfo(),
                        changeCurrency = fund.priceChangeCurrency.toCoinsInfo()
                    )
                }

                curState.copy(
                    topPopularFunds = updated,
                    myFunds = muFunds,
                    myBalance = curState.myBalance?.copy(
                        changeCurrency24Hr = muFunds.values.sumOf { fund ->
                        (fund.changeCurrency?.value ?: 0).toDouble()
                    }.toCoinsInfo(),
                        totalCoinsPrice = muFunds.values.sumOf { fund -> fund.totalCoinsPrice.value }.toCoinsInfo()
                    )
                )
            }
        }.launchIn(viewModelScope)
    }

}