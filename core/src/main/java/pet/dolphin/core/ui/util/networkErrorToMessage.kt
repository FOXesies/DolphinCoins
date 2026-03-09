package pet.dolphin.core.ui.util

import androidx.annotation.StringRes
import pet.dolphin.core.R
import pet.dolphin.core.domain.util.NetworkError

@StringRes
internal fun NetworkError.networkErrorToMessageRes(): Int {
    return when (this) {
        NetworkError.REQUEST_TIMEOUT -> R.string.error_request_timeout
        NetworkError.TOO_MANY_REQUEST -> R.string.error_too_many_requests
        NetworkError.NO_INTERNET -> R.string.error_no_internet
        NetworkError.SERVER_ERROR -> R.string.error_server_error
        NetworkError.SERIALIZATION -> R.string.error_serialization
        NetworkError.UNKNOWN -> R.string.error_unknown
    }
}