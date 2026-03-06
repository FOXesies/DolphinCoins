package pet.dolphin.home.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class TopPopularResponseDto(
    val data: List<FundPreviewDto>
)