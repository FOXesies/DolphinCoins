package pet.dolphin.home.domain.usecase

import pet.dolphin.core.domain.ErrorDomain
import pet.dolphin.core.domain.Result
import pet.dolphin.home.domain.model.FundPreview
import pet.dolphin.home.domain.repository.HomeRepository

class GetTopPopularFundsUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(limit: Int = 20): Result<List<FundPreview>, ErrorDomain> {
        return repository.getTopPopularFunds(limit)
    }
}