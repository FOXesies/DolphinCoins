package pet.dolphin.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pet.dolphin.home.presentation.model.BalanceActionButton
import pet.dolphin.home.presentation.model.BalanceActionType
import pet.dolphin.home.presentation.model.BalanceUI
import pet.dolphin.ui.R
import pet.dolphin.ui.ThemeShapes
import pet.eat.ui.LocalColorsPalette

@Composable
fun HomeBalance(
    modifier: Modifier = Modifier,
    balanceUi: BalanceUI
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(LocalColorsPalette.current.container)
            .padding(vertical = 16.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            stringResource(R.string.home_balance_title),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = LocalColorsPalette.current.onContainer
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                balanceUi.balanceTotalDisplay.localCurrencySymbol.plus(balanceUi.balanceTotalDisplay.totalForView),
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.ExtraBold,
                color = LocalColorsPalette.current.onBackground,
                fontSize = 26.sp
            )

            Column(/*
                verticalArrangement = Arrangement.spacedBy(8.dp)*/
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    "+ ".plus(balanceUi.balanceTotalDisplay.localCurrencySymbol).plus(balanceUi.balanceTotalDisplay.differenceLocalCurrency),
                    fontWeight = FontWeight.Medium,
                    color = LocalColorsPalette.current.onBackground,
                    fontSize = 16.sp
                )

                Text(
                    "+ ".plus(balanceUi.balanceTotalDisplay.differencePercentage).plus("%"),
                    fontWeight = FontWeight.Medium,
                    color = LocalColorsPalette.current.tertiary,
                    fontSize = 16.sp
                )
            }
        }

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

private val buttonsAction = listOf(
    BalanceActionButton(
        name = R.string.home_balance_action_funds_up,
        type = BalanceActionType.UpFunds,
        imageRes = R.drawable.arrow_down
    ),
    BalanceActionButton(
        name = R.string.home_balance_action_withdraw,
        type = BalanceActionType.Withdraw,
        imageRes = R.drawable.arrow_up
    ),
)

@Composable
private fun BalanceActionItem(
    modifier: Modifier,
    buttonInfo: BalanceActionButton
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(ThemeShapes.medium))
            .background(LocalColorsPalette.current.onContainer)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(buttonInfo.imageRes),
            modifier = Modifier
                .size(24.dp),
            tint = LocalColorsPalette.current.onBackground,
            contentDescription = "ic_action"
        )

        Spacer(Modifier.width(6.dp))

        Text(stringResource(buttonInfo.name),
            fontWeight = FontWeight.Medium,
            color = LocalColorsPalette.current.onBackground,
            fontSize = 16.sp
        )
    }
}