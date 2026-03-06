package pet.dolphin.home.presentation.balance.model

sealed class BalanceActionTypeUi {
    object UpFunds : BalanceActionTypeUi()
    object Withdraw : BalanceActionTypeUi()
    object History : BalanceActionTypeUi()
    object Stats : BalanceActionTypeUi()
}