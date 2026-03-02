package pet.dolphin.home.data.mappers

import pet.dolphin.home.data.remote.dto.FundPreviewDto
import pet.dolphin.home.domain.model.FundPreview
import pet.dolphin.home.presentation.fund.model.FundUI
import pet.dolphin.home.presentation.model.DisplayableNumber
import java.text.NumberFormat
import java.util.Locale

fun FundPreviewDto.toDomain() = FundPreview(
    id = id,
    symbol = symbol,
    rank = rank,
    name = name,
    marketCapUsd = marketCapUsd,
    priceUsd = priceUsd,
    changePercent24Hr = changePercent24Hr,
)

fun FundPreview.toUi() = FundUI(
    symbol = symbol,
    fullName = name,
    totalCoinsPrice = marketCapUsd.toCoinsInfo(),
    changePercent24Hr = changePercent24Hr.toPercentInfo(),
    logoImg = "null",
    changeCurrency24Hr = changePercent24Hr.toPercentInfo(),
)


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