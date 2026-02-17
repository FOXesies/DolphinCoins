package pet.dolphin.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pet.dolphin.home.presentation.components.HomeBalance
import pet.dolphin.home.presentation.components.TopHomeBar
import pet.dolphin.home.presentation.model.BalanceTotalDisplay
import pet.dolphin.home.presentation.model.BalanceUI
import pet.dolphin.ui.R
import pet.eat.ui.LocalColorsPalette

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LocalColorsPalette.current.background)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            // Top Bar (Profile & Navigation)
            TopHomeBar(
                "",
                "Smart People"
            )

            Spacer(Modifier.height(20.dp))

            // Widget Balance
            HomeBalance(
                balanceUi = balanceUI
            )
        }
    }
}

private val balanceUI = BalanceUI(
    id = "1asff",
    balanceTotalDisplay = BalanceTotalDisplay(
        totalForView = "27,421.42",
        differencePercentage = "2.7",
        differenceLocalCurrency = "741.35",
        localCurrencySymbol = stringResource(R.string.dollar_marker)
    )
)

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}