package pet.dolphin.auth.domain.usecase

import pet.dolphin.auth.domain.repository.AuthRepository
import pet.dolphin.core.domain.util.ErrorDomain
import pet.dolphin.core.domain.util.Result

class SignUpUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(login: String, email: String, password: String): Result<Unit, ErrorDomain> {
        return repository.signUp(login = login, email = email, password = password)
    }
}