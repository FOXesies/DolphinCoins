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
import pet.dolphin.home.presentation.fund.model.FundUI
import pet.dolphin.ui.widgets.DotSeparator
import pet.eat.ui.LocalColorsPalette

@Composable
fun PriceCoinWithDifference(
    alignment: Alignment.Horizontal = Alignment.Start,
    fundUi: FundUI
){
    val isPositiveChange = fundUi.changePercent24Hr.value > 0.0
    val colorChanges = if(isPositiveChange) LocalColorsPalette.current.secondary else LocalColorsPalette.current.error

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

        // Difference percentage and cache with localCurrencySymbol
        Row(
            horizontalArrangement = Arrangement.spacedBy(1.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                fundUi.changeCurrency24Hr.formatted,
                fontWeight = FontWeight.Normal,
                color = colorChanges,
                fontSize = 14.sp
            )

            DotSeparator(
                color = LocalColorsPalette.current.onContainer
            )

            Text(
                fundUi.changePercent24Hr.formatted,
                fontWeight = FontWeight.Normal,
                color = colorChanges,
                fontSize = 14.sp
            )
        }
    }
}