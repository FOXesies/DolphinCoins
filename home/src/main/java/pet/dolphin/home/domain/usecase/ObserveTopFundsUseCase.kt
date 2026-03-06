package pet.dolphin.home.domain.usecase

import kotlinx.coroutines.flow.SharedFlow
import pet.dolphin.home.domain.model.FundPreview
import pet.dolphin.home.domain.model.FundPreviewWs
import pet.dolphin.home.domain.repository.HomeRepository

class ObserveTopFundsUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(fundsSymbols: Set<String>): SharedFlow<FundPreviewWs> {
        return repository.observeFunds(fundsSymbols)
    }
}