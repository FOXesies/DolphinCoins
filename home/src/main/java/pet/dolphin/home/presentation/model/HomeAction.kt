package pet.dolphin.home.presentation.model

sealed interface HomeAction {
    data object LoadData: HomeAction
    data class OnTapFound(val idFund: Int): HomeAction
    data object OnTapProfile: HomeAction
    data object Retry: HomeAction
}