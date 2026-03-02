package pet.dolphin.home.domain.model

data class FundPreview(
    val id: String,
    val rank: String,
    val name: String,
    val symbol: String,
    val priceUsd: Double,
    val changePercent24Hr: Double
)