package pet.dolphin.home.presentation.model

sealed class BalanceActionType {
    object UpFunds : BalanceActionType()
    object Withdraw : BalanceActionType()
    object History : BalanceActionType()
    object Stats : BalanceActionType()
}