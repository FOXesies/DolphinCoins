package pet.dolphin.auth.data.networking.dto

import com.google.firebase.Timestamp
import kotlinx.serialization.Serializable
import pet.dolphin.auth.mappers.toStringFormat
import java.time.ZonedDateTime

@Serializable
data class UserDto(
    val uid: String,
    val email: String,
    val login: String,
    val lastLogIn: String = ZonedDateTime.now().toStringFormat()
)
