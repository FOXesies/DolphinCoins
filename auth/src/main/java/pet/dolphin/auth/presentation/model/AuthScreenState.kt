package pet.dolphin.auth.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class AuthScreenState(
    val isLogin: Boolean = true,
    val isLoading: Boolean = false,
    val userInfo: UserInfoState = UserInfoState(),
    val errorMessage: String? = null
)