package pet.dolphin.home.presentation.model

import pet.dolphin.home.presentation.fund.model.FundUI

data class HomeScreenState(
    val topPopularFunds: Map<String, FundUI> = emptyMap(),
    val isLoading: Boolean = false,
    val myFunds: Map<String, FundUI> = emptyMap()
)
