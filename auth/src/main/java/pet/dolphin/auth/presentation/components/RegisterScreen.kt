package pet.dolphin.auth.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pet.dolphin.auth.R as LocalR
import pet.dolphin.auth.presentation.model.AuthAction
import pet.dolphin.auth.presentation.model.AuthScreenState


@Composable
fun RegisterScreen(
    login: String,
    phone: String,
    password: String,
    onAction: (AuthAction) -> Unit
) {
    Text(stringResource(LocalR.string.button_register))

    Spacer(modifier = Modifier.height(12.dp))

    TextField(
        login,
        modifier = Modifier.fillMaxWidth(),
        onValueChange = { changedValue ->
            onAction(AuthAction.ChangeLoginValue(changedValue))
        }
    )

    TextField(
        phone,
        modifier = Modifier.fillMaxWidth(),
        onValueChange = { changedValue ->
            onAction(AuthAction.ChangePhoneValue(changedValue))
        }
    )

    TextField(
        password,
        modifier = Modifier.fillMaxWidth(),
        onValueChange = { changedValue ->
            onAction(AuthAction.ChangePasswordValue(changedValue))
        }
    )

    Spacer(modifier = Modifier.height(4.dp))

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = { onAction(AuthAction.OnRegisterClick) }
        ) {
            Text(stringResource(LocalR.string.button_register))
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            stringResource(LocalR.string.label_have_account),
            modifier = Modifier
                .clickable(onClick = { onAction(AuthAction.SwapAuthScreen) })
        )
    }
}