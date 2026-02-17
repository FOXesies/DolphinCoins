package pet.dolphin.home.presentation.fund

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pet.dolphin.home.presentation.fund.components.NameCoinWithLogo
import pet.dolphin.home.presentation.fund.components.PriceCoinWithDifference
import pet.dolphin.home.presentation.fund.model.FundUI


@Composable
fun MyFundItem(
    modifier: Modifier = Modifier,
    fundUi: FundUI
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Name, symbol & logo coin's
        NameCoinWithLogo(
            fundUi = fundUi
        )

        // Mb add graphics change

        // Current price, difference precent & price coin's
        PriceCoinWithDifference(
            fundUi = fundUi
        )
    }
}




