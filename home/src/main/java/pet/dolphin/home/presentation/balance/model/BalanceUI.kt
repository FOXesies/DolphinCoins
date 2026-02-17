package pet.dolphin.home.presentation.balance.model

import pet.dolphin.home.presentation.model.DisplayableNumber

data class BalanceUI(
    val id: String,
    val totalCoinsPrice: DisplayableNumber,
    val changePercent24Hr: DisplayableNumber,
    val changeCurrency24Hr: DisplayableNumber,
    val localCurrencySymbol: String

)

