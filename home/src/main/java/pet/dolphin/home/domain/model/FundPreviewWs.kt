package pet.dolphin.home.domain.model

data class FundPreviewWs(
    val symbol: String,
    val currentPrice: Double,
    val priceChangePercent: Double,
    val priceChangeCurrency: Double
)