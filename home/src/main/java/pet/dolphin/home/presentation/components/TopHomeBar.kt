package pet.dolphin.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pet.dolphin.ui.R
import pet.dolphin.ui.ThemeShapes
import pet.eat.ui.LocalColorsPalette

@Composable
fun TopHomeBar(
    profileImg: String,
    nameProfile: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(ThemeShapes.circle),
            painter = painterResource(R.drawable.i),
            contentScale = ContentScale.Crop,
            contentDescription = "profile_img"
        )

        Text(
            text = nameProfile,
            modifier = Modifier.weight(1f),
            fontSize = 16.sp,
            color = LocalColorsPalette.current.onBackground,
            fontWeight = FontWeight.Medium
        )

        Icon(
            modifier = Modifier
                .clip(ThemeShapes.circle)
                .background(LocalColorsPalette.current.container)
                .size(44.dp)
                .padding(10.dp),
            tint = LocalColorsPalette.current.onBackground,
            imageVector = ImageVector.vectorResource(R.drawable.ic_notification),
            contentDescription = "notification_ic"
        )
    }
}