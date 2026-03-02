package pet.dolphin.home.presentation.model

sealed interface HomeAction {
    data object LoadData: HomeAction
    data class OnTapFund(val id: String): HomeAction
    data object OnTapProfile: HomeAction
    data object Retry: HomeAction
}