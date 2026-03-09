package pet.dolphin.core.ui.util

import android.content.Context
import pet.dolphin.core.R
import pet.dolphin.core.domain.util.AuthError
import pet.dolphin.core.domain.util.ErrorDomain
import pet.dolphin.core.domain.util.NetworkError

fun ErrorDomain.toString(context: Context): String {
    val resId = when(this) {
        is NetworkError -> this.networkErrorToMessageRes()
        is AuthError -> this.authErrorToMessageRes()
        else -> R.string.error_unknown
    }

    return context.getString(resId)
}