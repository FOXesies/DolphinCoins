package pet.dolphin.auth.data.networking.dto

import com.google.firebase.Timestamp
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val uid: String,
    val email: String,
    val login: String,
    val lastLogIn: String = Timestamp.now().toString()
)
