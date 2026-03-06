package pet.dolphin.home.presentation.model

import pet.dolphin.home.presentation.balance.model.BalanceUI
import pet.dolphin.home.presentation.fund.model.FundUI

data class HomeScreenState(
    val topPopularFunds: Map<String, FundUI> = emptyMap(),
    val isLoading: Boolean = false,
    val myBalance: BalanceUI? = null,
    val myFunds: Map<String, FundUI> = emptyMap()
)
