package pet.dolphin.home.data.repository

import pet.dolphin.core.domain.util.ErrorDomain
import pet.dolphin.core.domain.util.Result
import pet.dolphin.home.data.remote.HomeRemoteSource
import pet.dolphin.home.domain.model.FundPreview
import pet.dolphin.home.domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val remoteSource: HomeRemoteSource
): HomeRepository {
    override suspend fun getTopPopularFunds(limit: Int): Result<List<FundPreview>, ErrorDomain> {
        return remoteSource.getTopPopularFunds(limit)
    }
}