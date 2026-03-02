package pet.dolphin.home.domain.usecase

import pet.dolphin.core.domain.util.ErrorDomain
import pet.dolphin.core.domain.util.Result
import pet.dolphin.home.domain.model.FundPreview
import pet.dolphin.home.domain.repository.HomeRepository

class GetTopPopularFundsUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(limit: Int = 20): Result<List<FundPreview>, ErrorDomain> {
        return repository.getTopPopularFunds(limit)
    }
}