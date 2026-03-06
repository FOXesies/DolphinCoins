package pet.dolphin.home.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FundPreviewWsDto(
    @SerialName("s")
    val symbol: String,

    @SerialName("c")
    val currentPrice: String,

    @SerialName("P")
    val priceChangePercent: String,

    @SerialName("p")
    val priceChangeCurrency: String
)