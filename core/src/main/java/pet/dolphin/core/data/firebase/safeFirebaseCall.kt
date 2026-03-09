package pet.dolphin.core.data.firebase

import android.util.Log
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.ensureActive
import pet.dolphin.core.domain.util.AuthError
import pet.dolphin.core.domain.util.ErrorDomain
import pet.dolphin.core.domain.util.NetworkError
import pet.dolphin.core.domain.util.Result
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeFirebaseCall(
    execute: suspend () -> T
): Result<T, ErrorDomain> {
    return try {
        val result = execute()
        Result.Success(result)
    } catch (e: Exception) {
        Log.e("SafeFirebaseCall", "Exception caught: ${e::class.simpleName} - ${e.message}", e)
        coroutineContext.ensureActive()

        val error: ErrorDomain = when (e) {
            is FirebaseNetworkException -> NetworkError.NO_INTERNET
            is FirebaseAuthInvalidCredentialsException -> AuthError.INVALID_CREDENTIALS
            is FirebaseAuthInvalidUserException -> AuthError.USER_NO_FOUND
            is FirebaseTooManyRequestsException -> NetworkError.TOO_MANY_REQUEST
            is FirebaseAuthEmailException -> AuthError.EMAIL_ALREADY_EXISTS
            is CustomFirebaseException.LoginNoUniqueException -> AuthError.LOGIN_ALREADY_EXISTS
            else -> NetworkError.UNKNOWN
        }

        Result.Error(error)
    }
}