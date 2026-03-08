package pet.dolphin.core.domain.util

enum class AuthError: ErrorDomain {
    USER_NO_FOUND,
    INVALID_CREDENTIALS,
    EMAIL_ALREADY_EXISTS,
    WEAK_PASSWORD,
    PASSWORDS_NO_MATCH
}