package pet.dolphin.home.presentation.model

data class BalanceUI(
    val id: String,
    val balanceTotalDisplay: BalanceTotalDisplay,

)

data class BalanceTotalDisplay(
    val totalForView: String,
    val differencePercentage: String,
    val differenceLocalCurrency: String,
    val localCurrencySymbol: String
)
