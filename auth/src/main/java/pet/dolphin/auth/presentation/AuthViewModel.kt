package pet.dolphin.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pet.dolphin.auth.domain.usecase.SignInUseCase
import pet.dolphin.auth.domain.usecase.SignUpUseCase
import pet.dolphin.auth.presentation.model.AuthAction
import pet.dolphin.auth.presentation.model.AuthEvent
import pet.dolphin.auth.presentation.model.AuthScreenState
import pet.dolphin.auth.presentation.model.Effect
import pet.dolphin.auth.presentation.model.UserInfoState
import pet.dolphin.core.domain.util.onError
import pet.dolphin.core.domain.util.onSuccess

class AuthViewModel(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase
): ViewModel() {

    private val _state = MutableStateFlow(AuthScreenState())
    val state = _state.asStateFlow()
        .stateIn(viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            AuthScreenState()
        )

    private val _effect = MutableSharedFlow<Effect>()
    val effect = _effect.asSharedFlow()

    private val _event = MutableSharedFlow<AuthEvent>(
        replay = 0,
        extraBufferCapacity = 1
    )
    val event = _event.asSharedFlow()

    fun onAction(action: AuthAction){
        when(action) {
            is AuthAction.ChangeLoginValue -> _state.update { it.copy(
                userInfo = it.userInfo.copy(login = action.data)
            ) }
            is AuthAction.ChangePasswordValue -> _state.update { it.copy(
                userInfo = it.userInfo.copy(password = action.data)
            ) }
            is AuthAction.ChangeEmailValue -> _state.update { it.copy(
                userInfo = it.userInfo.copy(email = action.data)
            ) }
            AuthAction.OnLoginClick -> { signIn() }
            AuthAction.OnRegisterClick -> { signUp() }
            AuthAction.SwapAuthScreen -> _state.update { it.copy(
                isLogin = !it.isLogin,
                userInfo = UserInfoState()
            ) }
        }
    }

    private fun signIn(){
        viewModelScope.launch {
            val userInputData = _state.value.userInfo

            // Send simple password, because he encrypted by
            // services Firebase when sending (key unique for each account)
            signInUseCase.invoke(
                email = userInputData.email,
                password = userInputData.password
            )
            .onSuccess {
                sendEvent(AuthEvent.SuccessLogin)
                delay(100)
                sendEffect(Effect.NavigateHome)
            }
            .onError { error ->
                sendEvent(AuthEvent.Error(error))
            }
        }
    }

    private fun signUp(){
        viewModelScope.launch {
            val userInputData = _state.value.userInfo

            // Send simple password, because he encrypted by
            // services Firebase when sending (key unique for each account)
            signUpUseCase.invoke(
                login = userInputData.login,
                email = userInputData.email,
                password = userInputData.password
            )
            .onSuccess {
                sendEvent(AuthEvent.SuccessLogin)
                delay(100)
                sendEffect(Effect.NavigateHome)
            }
            .onError { error ->
                sendEvent(AuthEvent.Error(error))
            }
        }
    }

    private fun sendEffect(effect: Effect) {
        viewModelScope.launch { _effect.emit(effect) }
    }
    private fun sendEvent(event: AuthEvent) {
        viewModelScope.launch { _event.emit(event) }
    }
}