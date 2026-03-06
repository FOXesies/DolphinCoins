package pet.dolphin.home.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import pet.dolphin.core.domain.util.ErrorDomain
import pet.dolphin.core.domain.util.Result
import pet.dolphin.home.domain.model.FundPreview
import pet.dolphin.home.domain.model.FundPreviewWs

interface HomeRepository {
    suspend fun getTopPopularFunds(limit: Int): Result<List<FundPreview>, ErrorDomain>
    suspend fun observeFunds(fundsSymbols: Set<String>): SharedFlow<FundPreviewWs>
}