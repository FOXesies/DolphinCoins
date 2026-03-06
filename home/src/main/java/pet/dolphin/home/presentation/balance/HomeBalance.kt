package pet.dolphin.home.presentation.balance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pet.dolphin.core.R
import pet.dolphin.core.ui.LocalColorsPalette
import pet.dolphin.home.presentation.balance.components.BalanceActionItem
import pet.dolphin.home.presentation.balance.model.BalanceActionButtonUi
import pet.dolphin.home.presentation.balance.model.BalanceActionTypeUi
import pet.dolphin.home.presentation.balance.model.BalanceUI
import pet.dolphin.home.R as LocalR

@Composable
fun HomeBalance(
    modifier: Modifier = Modifier,
    balanceUi: BalanceUI?
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            stringResource(LocalR.string.balance_title),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = LocalColorsPalette.current.onContainer
        )

        if(balanceUi != null) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Total cache with localCurrencySymbol
                Text(
                    balanceUi.totalCoinsPrice.formatted,
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.ExtraBold,
                    color = LocalColorsPalette.current.onBackground,
                    fontSize = 26.sp
                )

                // Difference percentage and cache with localCurrencySymbol
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        balanceUi.changeCurrency24Hr.formatted,
                        fontWeight = FontWeight.ExtraBold,
                        color = LocalColorsPalette.current.onBackground,
                        fontSize = 16.sp
                    )

                    Text(
                        balanceUi.changePercent24Hr.formatted,
                        fontWeight = FontWeight.Medium,
                        color = LocalColorsPalette.current.secondary,
                        fontSize = 16.sp
                    )
                }
            }

            // Actions with balance
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                val modifierBalanceActionButton = Modifier.weight(1f)
                buttonsAction.forEach { buttonInfo ->
                    BalanceActionItem(modifierBalanceActionButton, buttonInfo)
                }
            }
        }

    }
}

private val buttonsAction = listOf(
    BalanceActionButtonUi(
        name = LocalR.string.balance_action_funds_up,
        type = BalanceActionTypeUi.UpFunds,
        imageRes = R.drawable.arrow_down
    ),
    BalanceActionButtonUi(
        name = LocalR.string.balance_action_withdraw,
        type = BalanceActionTypeUi.Withdraw,
        imageRes = R.drawable.arrow_up
    ),
)
