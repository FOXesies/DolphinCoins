package pet.dolphin.core.domain

enum class LocalError: ErrorDomain {
    DATABASE_NOT_INITIALIZED,
    DATABASE_FULL_DISK,
    DATABASE_LOCKED,
    NO_DATA_FOUND,
    UNKNOWN
}