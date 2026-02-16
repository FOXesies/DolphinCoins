package pet.dolphin.home.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pet.dolphin.home.presentation.model.BalanceActionButton
import pet.dolphin.home.presentation.model.BalanceActionType
import pet.dolphin.ui.R
import pet.eat.ui.LocalColorsPalette

@Composable
fun HomeBalance(
    modifier: Modifier,
    balance: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .padding(vertical = 20.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(stringResource(R.string.home_balance_title),
            fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
            color = LocalColorsPalette.current.onContainer
        )

        Text(stringResource(R.string.dollar_marker).plus(balance),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 26.sp
        )

        Text(stringResource(R.string.dollar_marker).plus(balance),
            fontWeight = FontWeight.Medium,
            color = LocalColorsPalette.current.tertiary,
            fontSize = 16.sp
        )

        buttonsAction

    }
}

internal val buttonsAction = listOf(
    BalanceActionButton(
        name = R.string.home_balance_action_funds_up,
        type = BalanceActionType.UpFunds,
        imageRes = R.drawable.arrow_up
    ),
    BalanceActionButton(
        name = R.string.home_balance_action_withdraw,
        type = BalanceActionType.Withdraw,
        imageRes = R.drawable.arrow_up
    ),
    BalanceActionButton(
        name = R.string.home_balance_action_history,
        type = BalanceActionType.History,
        imageRes = R.drawable.arrow_up
    ),
)

@Composable
fun BalanceActionItem(

) {

}