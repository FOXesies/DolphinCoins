package pet.dolphin.auth.domain.usecase

import pet.dolphin.auth.domain.repository.AuthRepository
import pet.dolphin.core.domain.util.ErrorDomain
import pet.dolphin.core.domain.util.Result

class SignInUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<Unit, ErrorDomain> {
        return repository.signIn(email = email, password = password)
    }
}