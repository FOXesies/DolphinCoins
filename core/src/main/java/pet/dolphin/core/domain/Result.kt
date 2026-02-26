package pet.dolphin.core.domain

sealed interface Result<out D, out ErrorDomain> {
    data class Success<out D>(val data: D): Result<D, Nothing>
    data class Error<out E>(val error: E): Result<Nothing, E>
}

inline fun <T, E: ErrorDomain> Result<T, E>.onSuccess(action: (T) -> Unit): Result<T, E> {
    return when(this){
        is Result.Success -> {
            action(this.data)
            this
        }
        is Result.Error -> this
    }
}

inline fun <T, E: ErrorDomain> Result<T, E>.onError(action: (E) -> Unit): Result<T, E> {
    return when(this){
        is Result.Error -> {
            action(this.error)
            this
        }
        is Result.Success -> this
    }
}

inline fun <T, E: Error, R> Result<T, E>.map(map: (T) -> R): Result<R, E> {
    return when(this) {
        is Result.Error -> Result.Error(error)
        is Result.Success -> Result.Success(map(data))
    }
}
