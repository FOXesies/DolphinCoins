package pet.eat.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import pet.dolphin.core.ui.DarkColorScheme
import pet.dolphin.core.ui.LightColorScheme
import pet.dolphin.core.ui.LocalColorsPalette


@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    CompositionLocalProvider(
        LocalColorsPalette provides colorScheme
    ) {
        content()
    }
}