package pet.dolphin.home.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import pet.dolphin.core.data.networking.constructUrl
import pet.dolphin.core.data.networking.safeNetworkCall
import pet.dolphin.core.domain.util.NetworkError
import pet.dolphin.core.domain.util.Result
import pet.dolphin.core.domain.util.map
import pet.dolphin.home.data.mappers.toDomain
import pet.dolphin.home.data.remote.dto.TopPopularResponseDto
import pet.dolphin.home.domain.model.FundPreview

class HomeRemoteSource(
    private val httpClient: HttpClient
) {
    suspend fun getTopPopularFunds(limit: Int): Result<List<FundPreview>, NetworkError> {
        return safeNetworkCall<TopPopularResponseDto> {
            httpClient.get(
                urlString = constructUrl("assets")
            ){
                parameter("limit", limit)
            }
        }.map { response ->
            response.data.map { it.toDomain() }
        }
    }
}