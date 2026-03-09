package pet.dolphin.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.koinViewModel
import pet.dolphin.auth.R as LocalR
import pet.dolphin.auth.presentation.components.LoginContent
import pet.dolphin.auth.presentation.components.RegisterContent
import pet.dolphin.auth.presentation.model.AuthAction
import pet.dolphin.auth.presentation.model.AuthEvent
import pet.dolphin.auth.presentation.model.AuthScreenState
import pet.dolphin.auth.presentation.model.UserInfoState
import pet.dolphin.core.ui.util.showToastMessage
import pet.dolphin.core.ui.util.toString
import kotlin.coroutines.CoroutineContext


@Composable
fun AuthScreenRoot(
    modifier: Modifier,
    viewModel: AuthViewModel = koinViewModel(),
    onNavigationHome: (UserInfoState) -> Unit
){
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
            withContext(Dispatchers.Main.immediate) {
                viewModel.event.collect { event ->
                    when (event) {
                        is AuthEvent.Error -> showToastMessage(
                            context,
                            event.errorMessage.toString(context)
                        )
                        AuthEvent.SuccessLogin -> showToastMessage(
                            context,
                            context.getString(LocalR.string.login_success_message)
                        )
                        AuthEvent.SuccessRegister -> showToastMessage(
                            context,
                            context.getString(LocalR.string.register_success_message)
                        )
                    }
                }
            }
        }


    }

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


    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .padding(horizontal = 20.dp)
    ) {
        if (state.isLogin)
            LoginContent(
                state.userInfo.login,
                state.userInfo.password,
                onAction
            )
        else
            RegisterContent(
                state.userInfo.login,
                state.userInfo.email,
                state.userInfo.password,
                onAction
            )
    }
}