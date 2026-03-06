package pet.dolphin.home.presentation.fund.model

import pet.dolphin.home.presentation.model.DisplayableNumber

data class FundUI(
    val symbol: String,
    val fullName: String,
    val logoImg: String,
    val totalCoinsPrice: DisplayableNumber,
    val changePercent: DisplayableNumber,
    val changeCurrency: DisplayableNumber? = null
)