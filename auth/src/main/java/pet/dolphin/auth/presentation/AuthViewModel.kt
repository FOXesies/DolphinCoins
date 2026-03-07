package pet.dolphin.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import pet.dolphin.auth.presentation.model.AuthAction
import pet.dolphin.auth.presentation.model.AuthScreenState
import pet.dolphin.auth.presentation.model.Effect
import pet.dolphin.auth.presentation.model.UserInfoState

class AuthViewModel(

): ViewModel() {

    private val _state = MutableStateFlow(AuthScreenState())
    val state = _state.asStateFlow()
        .stateIn(viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            AuthScreenState()
        )

    private val _effect = Channel<Effect>()
    val effect = _effect.receiveAsFlow()

    fun onAction(action: AuthAction){
        when(action) {
            is AuthAction.ChangeLoginValue -> _state.update { it.copy(
                userInfo = it.userInfo.copy(login = action.data)
            ) }
            is AuthAction.ChangePasswordValue -> _state.update { it.copy(
                userInfo = it.userInfo.copy(password = action.data)
            ) }
            is AuthAction.ChangePhoneValue -> _state.update { it.copy(
                userInfo = it.userInfo.copy(phone = action.data)
            ) }
            AuthAction.OnLoginClick -> {}
            AuthAction.OnRegisterClick -> {}
            AuthAction.SwapAuthScreen -> _state.update { it.copy(
                isLogin = !it.isLogin,
                userInfo = UserInfoState()
            ) }
        }
    }

}