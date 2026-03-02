package pet.dolphin.core.domain.util

enum class NetworkError: ErrorDomain {
    REQUEST_TIMEOUT,
    TOO_MANY_REQUEST,
    NO_INTERNET,
    SERVER_ERROR,
    SERIALIZATION,
    UNKNOWN
}