package pet.dolphin.auth.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
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

    Column {
        TextField(
            state.userInfo.login,
            onValueChange = { changedValue ->
                onAction(AuthAction.ChangeLoginValue(changedValue))
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            state.userInfo.phone,
            onValueChange = { changedValue ->
                onAction(AuthAction.ChangePhoneValue(changedValue))
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            state.userInfo.password,
            onValueChange = { changedValue ->
                onAction(AuthAction.ChangePasswordValue(changedValue))
            }
        )

    }

}