package pet.dolphin.home.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FundWsDto(
    @SerialName("s")
    val symbol: String,

    @SerialName("c")
    val priceUsd: String,

    @SerialName("p")
    val priceChange: String,

    @SerialName("P")
    val priceChangePercent: String
)