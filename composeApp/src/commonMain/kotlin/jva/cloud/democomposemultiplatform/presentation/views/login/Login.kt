package jva.cloud.democomposemultiplatform.presentation.views.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Checkbox
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
import democomposemultiplatform.composeapp.generated.resources.name_label
import democomposemultiplatform.composeapp.generated.resources.new_to_app_text
import democomposemultiplatform.composeapp.generated.resources.password_label
import democomposemultiplatform.composeapp.generated.resources.sign_in_button
import democomposemultiplatform.composeapp.generated.resources.user_label
import jva.cloud.democomposemultiplatform.presentation.components.LoadingIndicator
import jva.cloud.democomposemultiplatform.presentation.components.MyAlertDialog
import jva.cloud.democomposemultiplatform.presentation.components.MyButton
import jva.cloud.democomposemultiplatform.presentation.components.MyOutLinedTextField
import jva.cloud.democomposemultiplatform.presentation.components.SectionHeader
import jva.cloud.democomposemultiplatform.presentation.components.model.AlertType
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

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LoadingIndicator(enabled = state.isLoading, modifier = Modifier)
        MyAlertDialog(
            showDialog = state.showDialog,
            title = stringResource(Res.string.error_title),
            text = getErrorMessage(loginError = state.loginError),
            alertType = AlertType.ERROR,
            onDismiss = { viewModel.onDialogDismiss() },
            onConfirm = { viewModel.onDialogDismiss() }
        )

        LoginForm(state, viewModel, redirectHome, redirectCreateAccount)
    }
}

@Composable
private fun LoginForm(
    state: LoginViewModelState,
    viewModel: LoginViewModel,
    redirectHome: () -> Unit,
    redirectCreateAccount: () -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(state = rememberScrollState())
            .fillMaxWidth()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (state.loggedIn) redirectHome()
        Text(
            modifier = Modifier.padding(vertical = 35.dp),
            text = stringResource(Res.string.login_title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
        )
        SectionHeader(
            text = stringResource(Res.string.name_label),
            icon = Icons.Default.Person,
            modifier = Modifier.align(Alignment.Start).padding(start = 33.dp)
        )
        MyOutLinedTextField(
            modifier = Modifier.padding(bottom = 25.dp),
            text = state.user,
            label = stringResource(Res.string.user_label),
            onValueChange = { viewModel.updateParameterStatus(user = it) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        SectionHeader(
            text = stringResource(Res.string.password_label),
            icon = Icons.Default.Lock,
            modifier = Modifier.align(Alignment.Start).padding(start = 33.dp)
        )
        MyOutLinedTextField(
            modifier = Modifier.padding(bottom = 20.dp),
            text = state.password,
            label = stringResource(Res.string.password_label),
            onValueChange = { viewModel.updateParameterStatus(password = it) },
            isPasswordField = true
        )
        Row(
            modifier = Modifier.align(Alignment.Start).padding(start = 25.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                modifier = Modifier,
                checked = state.rememberMe,
                onCheckedChange = { viewModel.updateRememberMeStatus() })
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "Remember me")


        }
        MyButton(
            text = stringResource(Res.string.sign_in_button),
            enabled = state.enabledButtonLogin,
            onClick = { viewModel.login() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp).height(56.dp)
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
