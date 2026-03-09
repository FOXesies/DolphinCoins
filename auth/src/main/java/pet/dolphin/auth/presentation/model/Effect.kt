package pet.dolphin.auth.presentation.model

sealed interface Effect {
    data object NavigateHome: Effect
}