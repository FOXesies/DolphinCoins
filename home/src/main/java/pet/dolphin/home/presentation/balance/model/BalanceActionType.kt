package pet.dolphin.home.presentation.balance.model

sealed class BalanceActionType {
    object UpFunds : BalanceActionType()
    object Withdraw : BalanceActionType()
    object History : BalanceActionType()
    object Stats : BalanceActionType()
}