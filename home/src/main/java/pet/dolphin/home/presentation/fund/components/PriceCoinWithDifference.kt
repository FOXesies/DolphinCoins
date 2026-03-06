package pet.dolphin.home.presentation.fund.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pet.dolphin.core.ui.LocalColorsPalette
import pet.dolphin.home.presentation.fund.model.FundUI
import pet.dolphin.core.ui.widgets.DotSeparator

@Composable
fun PriceCoinWithDifference(
    alignment: Alignment.Horizontal = Alignment.Start,
    fundUi: FundUI
){
    val isChange = fundUi.changePercent.value != 0.0

    Column(
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalAlignment = alignment
    ) {
        // Current price coin
        Text(
            fundUi.totalCoinsPrice.formatted,
            modifier = Modifier,
            fontWeight = FontWeight.Medium,
            color = LocalColorsPalette.current.onBackground,
            fontSize = 16.sp
        )

        if(isChange) {
            // Difference percentage and cache with localCurrencySymbol
            Row(
                horizontalArrangement = Arrangement.spacedBy(1.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Get need color
                val colorChanges =
                    if (fundUi.changePercent.value > 0.0) LocalColorsPalette.current.secondary else LocalColorsPalette.current.error

                fundUi.changeCurrency?.let {
                    Text(
                        fundUi.changeCurrency.formatted,
                        fontWeight = FontWeight.Normal,
                        color = colorChanges,
                        fontSize = 14.sp
                    )

                    DotSeparator(
                        color = LocalColorsPalette.current.onContainer
                    )
                }

                Text(
                    fundUi.changePercent.formatted,
                    fontWeight = FontWeight.Normal,
                    color = colorChanges,
                    fontSize = 14.sp
                )
            }
        }
    }
}