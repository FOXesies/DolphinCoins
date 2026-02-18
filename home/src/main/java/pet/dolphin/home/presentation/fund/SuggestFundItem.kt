package pet.dolphin.home.presentation.fund

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pet.dolphin.home.presentation.fund.components.NameCoinWithLogo
import pet.dolphin.home.presentation.fund.components.PriceCoinWithDifference
import pet.dolphin.home.presentation.fund.model.FundUI

@Composable
fun SuggestFundItem(
    modifier: Modifier = Modifier,
    fundUi: FundUI
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Name, symbol & logo coin's
        NameCoinWithLogo(
            fundUi = fundUi
        )

        Spacer(Modifier.weight(1f))
        // Mb add graphics change

        // Current price, difference precent & price coin's
        PriceCoinWithDifference(
            alignment = Alignment.End,
            fundUi = fundUi
        )
    }
}
