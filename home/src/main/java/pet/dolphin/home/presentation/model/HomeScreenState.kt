package pet.dolphin.home.presentation.model

import pet.dolphin.home.presentation.fund.model.FundUI

data class HomeScreenState(
    val topPopularFunds: List<FundUI> = emptyList(),
    val isLoading: Boolean = false,
    val myFunds: List<FundUI> = emptyList()
)
