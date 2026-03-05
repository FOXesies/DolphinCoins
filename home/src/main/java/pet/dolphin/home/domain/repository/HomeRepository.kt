package pet.dolphin.home.domain.repository

import kotlinx.coroutines.flow.Flow
import pet.dolphin.core.domain.util.ErrorDomain
import pet.dolphin.core.domain.util.Result
import pet.dolphin.home.domain.model.FundPreview

interface HomeRepository {
    suspend fun getTopPopularFunds(limit: Int): Result<List<FundPreview>, ErrorDomain>
    suspend fun connectToFundsWS(): Flow<List<FundPreview>>
}