package pet.dolphin.auth.presentation.model

sealed interface AuthAction {
    data class ChangeLoginValue(val data: String): AuthAction
    data class ChangePhoneValue(val data: String): AuthAction
    data class ChangePasswordValue(val data: String): AuthAction
}