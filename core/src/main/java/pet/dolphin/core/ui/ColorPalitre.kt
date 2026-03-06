package pet.dolphin.core.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import pet.eat.ui.Pink40
import pet.eat.ui.Purple40
import pet.eat.ui.PurpleGrey40


@Immutable
data class ColorPalette(
    val background: Color,
    val container: Color,
    val primary: Color,
    val secondary: Color,
    val error: Color = Color.Red,
    val tertiary: Color,
    val surface: Color,
    val onBackground: Color,
    val onContainer: Color,
    val onError: Color = Color.Red
)

val DarkColorScheme = ColorPalette(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = Color(0xFFFFFFFF),
    surface = Color(0xFF3B6F35),
    container = Color(0xFFF5F5F5),
    onBackground = Color(0xE9EEEFEF),
    onContainer = Color(0xE9EEEFEF),
    error = Color(0xE9EEEFEF)
)

val LightColorScheme = ColorPalette(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = Color(0xFF1E1E1F),
    surface = Color(0xFF3B6F35),
    container = Color(0xFF353636),
    onBackground = Color(0xE9F3F3F3),
    onContainer = Color(0xE9808080),
    error = Color(0xFFFF4141)
)

val LocalColorsPalette = staticCompositionLocalOf { LightColorScheme }