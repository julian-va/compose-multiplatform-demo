package jva.cloud.democomposemultiplatform.presentation.views.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import democomposemultiplatform.composeapp.generated.resources.Res
import democomposemultiplatform.composeapp.generated.resources.create_account_text
import democomposemultiplatform.composeapp.generated.resources.error_title
import democomposemultiplatform.composeapp.generated.resources.login_error_empty_fields
import democomposemultiplatform.composeapp.generated.resources.login_error_invalid_credentials
import democomposemultiplatform.composeapp.generated.resources.login_title
import democomposemultiplatform.composeapp.generated.resources.new_to_app_text
import democomposemultiplatform.composeapp.generated.resources.password_label
import democomposemultiplatform.composeapp.generated.resources.sign_in_button
import democomposemultiplatform.composeapp.generated.resources.user_label
import jva.cloud.democomposemultiplatform.presentation.components.LoadingIndicator
import jva.cloud.democomposemultiplatform.presentation.components.MyAlertDialog
import jva.cloud.democomposemultiplatform.presentation.components.MyOutLinedTextField
import jva.cloud.democomposemultiplatform.presentation.viewmodel.login.LoginError
import jva.cloud.democomposemultiplatform.presentation.viewmodel.login.LoginViewModel
import jva.cloud.democomposemultiplatform.presentation.viewmodel.login.LoginViewModelState
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Serializable
object Login

@Composable
fun LoginView(
    redirectHome: () -> Unit,
    redirectCreateAccount: () -> Unit,
    viewModel: LoginViewModel = koinViewModel()
) {
    val state: LoginViewModelState = viewModel.state
    val errorMessage = getErrorMessage(loginError = state.loginError)

    MyAlertDialog(
        showDialog = state.showDialog && errorMessage.isNotEmpty(),
        title = stringResource(Res.string.error_title),
        text = errorMessage,
        onDismiss = { viewModel.onDialogDismiss() },
        onConfirm = { viewModel.onDialogDismiss() }
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            15.dp,
            Alignment.CenterVertically
        )
    ) {
        LoadingIndicator(enabled = state.isLoading, modifier = Modifier)
        if (state.loggedIn) redirectHome()
        Text(
            text = stringResource(Res.string.login_title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
        )
        MyOutLinedTextField(
            text = state.user,
            label = stringResource(Res.string.user_label),
            onValueChange = { viewModel.updateParameterStatus(user = it) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        MyOutLinedTextField(
            text = state.password,
            label = stringResource(Res.string.password_label),
            onValueChange = { viewModel.updateParameterStatus(password = it) },
            isPasswordField = true
        )
        MyButton(
            text = stringResource(Res.string.sign_in_button),
            enabled = state.enabledButtonLogin,
            onClick = { viewModel.login() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 55.dp)
        )

        Row {
            Text(text = stringResource(Res.string.new_to_app_text))
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                modifier = Modifier.clickable(onClick = { redirectCreateAccount() }),
                text = stringResource(Res.string.create_account_text),
                color = MaterialTheme.colorScheme.primary
            )

        }
    }
}

@Composable
private fun getErrorMessage(loginError: LoginError?): String {
    return loginError?.let {
        when (it) {
            LoginError.EMPTY_FIELDS -> stringResource(Res.string.login_error_empty_fields)
            LoginError.INVALID_CREDENTIALS -> stringResource(Res.string.login_error_invalid_credentials)
        }
    } ?: ""
}

@Composable
private fun MyButton(
    text: String,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        enabled = enabled,
        onClick = { onClick() },
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text = text)
    }
}

/*@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    val viewModel: LoginViewModel = LoginViewModel()
    LoginView(viewModel = viewModel)
}**/
