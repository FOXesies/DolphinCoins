package pet.dolphin.home.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class FundWsResponseDto(
    val stream: String,
    val data: FundWsDto
)