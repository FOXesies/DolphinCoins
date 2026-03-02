package pet.dolphin.home.presentation.model

sealed interface Effect {
    data class NavigateDetail(val id: String) : Effect
}
