package pet.dolphin.auth.presentation.model

sealed interface AuthAction {
    data class ChangeLoginValue(val data: String): AuthAction
    data class ChangeEmailValue(val data: String): AuthAction
    data class ChangePasswordValue(val data: String): AuthAction
    data object SwapAuthScreen: AuthAction
    data object OnRegisterClick: AuthAction
    data object OnLoginClick: AuthAction
}