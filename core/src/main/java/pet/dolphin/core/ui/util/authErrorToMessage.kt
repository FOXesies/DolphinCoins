package pet.dolphin.core.ui.util

import androidx.annotation.StringRes
import pet.dolphin.core.R
import pet.dolphin.core.domain.util.AuthError

@StringRes
internal fun AuthError.authErrorToMessageRes(): Int {
    return when (this) {
        AuthError.USER_NO_FOUND -> R.string.error_user_not_found
        AuthError.INVALID_CREDENTIALS -> R.string.error_invalid_credentials
        AuthError.EMAIL_ALREADY_EXISTS -> R.string.error_email_already_exists
        AuthError.LOGIN_ALREADY_EXISTS -> R.string.error_login_already_exists
        AuthError.WEAK_PASSWORD -> R.string.error_weak_password
        AuthError.PASSWORDS_NO_MATCH -> R.string.error_passwords_no_match
    }
}