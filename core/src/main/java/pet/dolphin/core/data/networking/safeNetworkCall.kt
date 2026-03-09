package pet.dolphin.core.data.networking

import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
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