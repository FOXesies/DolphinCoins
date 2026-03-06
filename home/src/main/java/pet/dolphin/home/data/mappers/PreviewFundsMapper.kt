package pet.dolphin.home.data.mappers

import pet.dolphin.home.data.remote.dto.FundPreviewDto
import pet.dolphin.home.data.remote.dto.FundPreviewWsDto
import pet.dolphin.home.domain.model.FundPreview
import pet.dolphin.home.domain.model.FundPreviewWs
import pet.dolphin.home.presentation.fund.model.FundUI
import pet.dolphin.home.presentation.model.DisplayableNumber
import java.text.NumberFormat
import java.util.Locale

fun FundPreviewDto.toDomain() = FundPreview(
    symbol = symbol,
    name = name,
    priceUsd = priceUsd,
    changePercent = changePercent24Hr
)
fun FundPreviewWsDto.toDomain() = FundPreviewWs(
    symbol = symbol,
    currentPrice = currentPrice.toDouble(),
    priceChangePercent = priceChangePercent.toDouble(),
    priceChangeCurrency = priceChangeCurrency.toDouble(),
)

fun FundPreview.toUi() = FundUI(
    symbol = symbol,
    fullName = name,
    totalCoinsPrice = priceUsd.toCoinsInfo(),
    changePercent = changePercent.toPercentInfo(),
    changeCurrency = changePercent.toCoinsInfo(),
    logoImg = "null",
)

fun String.toBinanceSymbol(): String {
    return this.uppercase().plus("USDT")
}

fun String.toTicketParam(): String {
    return this.lowercase().plus("@ticker")
}

fun Double.toCoinsInfo(): DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }

    return DisplayableNumber(
        value = this,
        formatted = "$".plus(formatter.format(this))
    )
}

fun Double.toPercentInfo(): DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }

    return DisplayableNumber(
        value = this,
        formatted = formatter.format(this).plus("%")
    )
}