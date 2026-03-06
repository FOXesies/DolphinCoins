package pet.dolphin.core.ui.widgets

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import pet.dolphin.core.ui.ThemeShapes

@Composable
fun DotSeparator(
    size: Dp = 4.dp,
    color: Color
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 6.dp)
            .size(size)
            .clip(ThemeShapes.circle)
            .background(color)
    )
}