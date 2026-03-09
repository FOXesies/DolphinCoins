package pet.dolphin.core.navigation

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
sealed interface Screen {
    object Home: Screen
    object Auth: Screen
    data class DetailFund(val id: String): Screen
}