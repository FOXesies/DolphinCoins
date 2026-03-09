package pet.dolphin.core.data.networking

import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import pet.dolphin.core.data.firebase.CustomFirebaseException
import pet.dolphin.core.domain.util.AuthError
import pet.dolphin.core.domain.util.ErrorDomain
import pet.dolphin.core.domain.util.NetworkError
import pet.dolphin.core.domain.util.Result
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeNetworkCall(
    execute: () -> HttpResponse
): Result<T, NetworkError> {
    val response = try {
        execute()
    }
    catch (e: UnresolvedAddressException) {
        return Result.Error(NetworkError.NO_INTERNET)
    }
    catch (e: SerializationException){
        return Result.Error(NetworkError.SERIALIZATION)
    }
    catch (e: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(NetworkError.UNKNOWN)
    }

    return responseToResult(response)
}

suspend inline fun <reified T> safeFirebaseCall(
    execute: suspend () -> T
): Result<T, ErrorDomain> {
    return try {
        val result = execute()
        Result.Success(result)
    }
    catch (e: FirebaseNetworkException) {
        Result.Error(NetworkError.NO_INTERNET)
    }
    catch (e: FirebaseAuthInvalidCredentialsException){
        Result.Error(AuthError.INVALID_CREDENTIALS)
    }
    catch (e: FirebaseAuthInvalidUserException){
        Result.Error(AuthError.USER_NO_FOUND)
    }
    catch (e: FirebaseTooManyRequestsException) {
        Result.Error(NetworkError.TOO_MANY_REQUEST)
    }
    catch (e: FirebaseAuthEmailException) {
        Result.Error(AuthError.EMAIL_ALREADY_EXISTS)
    }
    catch (e: FirebaseAuthEmailException) {
        Result.Error(AuthError.EMAIL_ALREADY_EXISTS)
    }
    catch (e: CustomFirebaseException.LoginNoUniqueException) {
        Result.Error(AuthError.LOGIN_ALREADY_EXISTS)
    }
    catch (e: Exception) {
        coroutineContext.ensureActive()
        Result.Error(NetworkError.UNKNOWN)
    }
}