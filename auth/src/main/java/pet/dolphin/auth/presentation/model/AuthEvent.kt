package pet.dolphin.auth.presentation.model

import pet.dolphin.core.domain.util.ErrorDomain

sealed interface AuthEvent {
    data class Error(val errorMessage: ErrorDomain): AuthEvent
    object SuccessLogin: AuthEvent
    object SuccessRegister: AuthEvent
}