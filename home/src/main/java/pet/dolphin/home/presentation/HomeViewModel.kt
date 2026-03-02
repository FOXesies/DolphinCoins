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
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import pet.dolphin.core.domain.util.onError
import pet.dolphin.core.domain.util.onSuccess
import pet.dolphin.home.data.mappers.toUi
import pet.dolphin.home.domain.usecase.GetTopPopularFundsUseCase
import pet.dolphin.home.presentation.model.Effect
import pet.dolphin.home.presentation.model.HomeAction
import pet.dolphin.home.presentation.model.HomeEvent
import pet.dolphin.home.presentation.model.HomeScreenState

class HomeViewModel (
    private val getTopPopularFundsUseCase: GetTopPopularFundsUseCase
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
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    topPopularFunds = result.map { fund -> fund.toUi() }
                                )
                            }
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

}