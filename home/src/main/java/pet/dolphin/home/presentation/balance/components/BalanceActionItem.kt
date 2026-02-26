package pet.dolphin.home.presentation.balance.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pet.dolphin.home.presentation.balance.model.BalanceActionButton
import pet.dolphin.core.ui.ThemeShapes
import pet.eat.ui.LocalColorsPalette

@Composable
fun BalanceActionItem(
    modifier: Modifier,
    buttonInfo: BalanceActionButton
) {
    Row(
        modifier = modifier
            .clip(ThemeShapes.medium)
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