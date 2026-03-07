package pet.dolphin.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import pet.dolphin.auth.presentation.components.LoginScreen
import pet.dolphin.auth.presentation.components.RegisterScreen
import pet.dolphin.auth.presentation.model.AuthAction
import pet.dolphin.auth.presentation.model.AuthScreenState
import pet.dolphin.auth.presentation.model.UserInfoState


@Composable
fun AuthScreenRoot(
    modifier: Modifier,
    viewModel: AuthViewModel = koinViewModel(),
    onNavigationHome: (UserInfoState) -> Unit
){
    val state by viewModel.state.collectAsStateWithLifecycle()

    AuthScreen(
        state = state,
        onAction = viewModel::onAction
    )

}

@Composable
fun AuthScreen(
    state: AuthScreenState,
    onAction: (AuthAction) -> Unit
) {

    /*LaunchedEffect {
        // show error
    }*/

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .padding(horizontal = 20.dp)
    ) {
        if (state.isLogin)
            LoginScreen(
                state.userInfo.login,
                state.userInfo.password,
                onAction
            )
        else
            RegisterScreen(
                state.userInfo.login,
                state.userInfo.phone,
                state.userInfo.password,
                onAction
            )

    }
}