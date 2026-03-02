package pet.dolphin.home.presentation.fund.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pet.dolphin.core.R
import pet.dolphin.core.ui.LocalColorsPalette
import pet.dolphin.home.presentation.fund.model.FundUI

@Composable
fun NameCoinWithLogo(
    fundUi: FundUI
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(painter = painterResource(R.drawable.bitcoin), //using found.logoImg
            modifier = Modifier.size(40.dp),
            contentDescription = "ic_coin"
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(fundUi.fullName,
                color = LocalColorsPalette.current.onBackground,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            Text(fundUi.symbol,
                color = LocalColorsPalette.current.onContainer,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}
