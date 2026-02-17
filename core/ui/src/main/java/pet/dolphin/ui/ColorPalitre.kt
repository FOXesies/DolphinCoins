package pet.eat.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


@Immutable
data class ColorPalette(
    val background: Color,
    val container: Color,
    val primary: Color,
    val secondary: Color,
    val tertiary: Color,
    val surface: Color,
    val onBackground: Color,
    val onContainer: Color
)

val DarkColorScheme = ColorPalette(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = Color(0xFFFFFFFF),
    surface = Color(0xFF3B6F35),
    container = Color(0xFFF5F5F5),
    onBackground = Color(0xE9EEEFEF),
    onContainer = Color(0xE9EEEFEF)
)

val LightColorScheme = ColorPalette(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = Color(0xFF1B1C1E),
    surface = Color(0xFF3B6F35),
    container = Color(0xFF3F4040),
    onBackground = Color(0xE9F3F3F3),
    onContainer = Color(0xE99C9C9C)
)

val LocalColorsPalette = staticCompositionLocalOf { LightColorScheme }