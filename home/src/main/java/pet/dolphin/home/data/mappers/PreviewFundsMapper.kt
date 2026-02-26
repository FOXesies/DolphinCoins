package pet.dolphin.home.data.mappers

import pet.dolphin.home.data.remote.dto.FundPreviewDto
import pet.dolphin.home.domain.model.FundPreview

fun FundPreviewDto.toDomain() = FundPreview(
    id = id,
    symbol = symbol,
    rank = rank,
    name = name,
    marketCapUsd = marketCapUsd,
    priceUsd = priceUsd,
    changePercent24Hr = changePercent24Hr,
)