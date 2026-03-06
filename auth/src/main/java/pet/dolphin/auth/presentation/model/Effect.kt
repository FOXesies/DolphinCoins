package pet.dolphin.auth.presentation.model

sealed interface Effect {
    data class NavigateHome(val userInfoState: UserInfoState): Effect
}