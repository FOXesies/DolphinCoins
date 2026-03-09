package pet.dolphin.auth.domain.repository

import com.google.firebase.auth.FirebaseUser
import pet.dolphin.core.domain.util.ErrorDomain
import pet.dolphin.core.domain.util.Result

interface AuthRepository {
    suspend fun signUp(login: String, email: String, password: String): Result<Unit, ErrorDomain>
    suspend fun signIn(email: String, password: String): Result<Unit, ErrorDomain>
}