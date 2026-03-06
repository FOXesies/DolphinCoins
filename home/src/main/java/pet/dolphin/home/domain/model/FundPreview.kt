package pet.dolphin.home.domain.model

data class FundPreview(
    val name: String,
    val symbol: String,
    val priceUsd: Double,
    val changePercent: Double,
    val changeCurrency: Double? = null
)