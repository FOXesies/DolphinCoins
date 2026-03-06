package pet.dolphin.home.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import pet.dolphin.core.domain.util.ErrorDomain
import pet.dolphin.core.domain.util.Result
import pet.dolphin.home.data.remote.HomeRemoteSource
import pet.dolphin.home.data.remote.TopFundsWebSocketManager
import pet.dolphin.home.domain.model.FundPreview
import pet.dolphin.home.domain.model.FundPreviewWs
import pet.dolphin.home.domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val remoteSource: HomeRemoteSource,
): HomeRepository {
    override suspend fun getTopPopularFunds(limit: Int): Result<List<FundPreview>, ErrorDomain> {
        return remoteSource.getTopPopularFunds(limit)
    }

    override suspend fun observeFunds(fundsSymbols: Set<String>): SharedFlow<FundPreviewWs>  {
        return remoteSource.observeFunds(fundsSymbols)
    }
}