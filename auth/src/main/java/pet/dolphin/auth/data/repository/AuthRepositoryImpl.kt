package pet.dolphin.auth.data.repository

import com.google.firebase.auth.FirebaseUser
import pet.dolphin.auth.data.networking.AuthRemoteSource
import pet.dolphin.auth.domain.repository.AuthRepository
import pet.dolphin.core.domain.util.ErrorDomain
import pet.dolphin.core.domain.util.Result

class AuthRepositoryImpl(
    private val remoteSource: AuthRemoteSource
): AuthRepository {
    override suspend fun signUp(
        login: String,
        email: String,
        password: String
    ): Result<Unit, ErrorDomain> {
        return remoteSource.signUp(login, email, password)
    }

    override suspend fun signIn(email: String, password: String): Result<Unit, ErrorDomain> {
        return remoteSource.signIn(email, password)
    }
}