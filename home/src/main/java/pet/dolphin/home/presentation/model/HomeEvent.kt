package pet.dolphin.home.presentation.model

sealed interface HomeEvent {
    data class Error(val message: String): HomeEvent
}