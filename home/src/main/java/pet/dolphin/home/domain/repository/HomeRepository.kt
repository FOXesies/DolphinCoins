package pet.dolphin.home.domain.repository

import pet.dolphin.core.domain.ErrorDomain
import pet.dolphin.core.domain.Result
import pet.dolphin.home.domain.model.FundPreview

interface HomeRepository {
    suspend fun getTopPopularFunds(limit: Int): Result<List<FundPreview>, ErrorDomain>
}