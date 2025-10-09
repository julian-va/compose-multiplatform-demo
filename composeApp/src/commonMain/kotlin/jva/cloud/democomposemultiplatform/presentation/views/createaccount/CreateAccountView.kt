package jva.cloud.democomposemultiplatform.presentation.views.createaccount

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import democomposemultiplatform.composeapp.generated.resources.Res
import democomposemultiplatform.composeapp.generated.resources.account_created_successfully
import democomposemultiplatform.composeapp.generated.resources.already_have_account_text
import democomposemultiplatform.composeapp.generated.resources.create_account_button
import democomposemultiplatform.composeapp.generated.resources.create_account_title
import democomposemultiplatform.composeapp.generated.resources.email_label
import democomposemultiplatform.composeapp.generated.resources.email_placeholder
import democomposemultiplatform.composeapp.generated.resources.empty_fields_error
import democomposemultiplatform.composeapp.generated.resources.error_creating_account
import democomposemultiplatform.composeapp.generated.resources.error_title
import democomposemultiplatform.composeapp.generated.resources.full_name_placeholder
import democomposemultiplatform.composeapp.generated.resources.name_label
import democomposemultiplatform.composeapp.generated.resources.password_label
import democomposemultiplatform.composeapp.generated.resources.password_placeholder
import democomposemultiplatform.composeapp.generated.resources.sign_in_link
import democomposemultiplatform.composeapp.generated.resources.sign_up_subtitle
import democomposemultiplatform.composeapp.generated.resources.success_title
import jva.cloud.democomposemultiplatform.presentation.components.LoadingIndicator
import jva.cloud.democomposemultiplatform.presentation.components.MyAlertDialog
import jva.cloud.democomposemultiplatform.presentation.components.MyButton
import jva.cloud.democomposemultiplatform.presentation.components.MyOutLinedTextField
import jva.cloud.democomposemultiplatform.presentation.components.SectionHeader
import jva.cloud.democomposemultiplatform.presentation.components.model.AlertType
import jva.cloud.democomposemultiplatform.presentation.viewmodel.createaccount.CreateAccountError
import jva.cloud.democomposemultiplatform.presentation.viewmodel.createaccount.CreateAccountViewModel
import jva.cloud.democomposemultiplatform.presentation.viewmodel.createaccount.CreateAccountViewModelState
import jva.cloud.democomposemultiplatform.utils.ConstantApp.STRING_EMPTY
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Serializable
object CreateAccount

@Composable
fun CreateAccountView(onSignIn: () -> Unit, vm: CreateAccountViewModel = koinViewModel()) {
    val state = vm.state

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LoadingIndicator(enabled = state.isLoading, modifier = Modifier)
        CreateAccountForm(state, vm, onSignIn)
    }
}

@Composable
private fun CreateAccountForm(
    state: CreateAccountViewModelState,
    vm: CreateAccountViewModel,
    onSignIn: () -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(state = rememberScrollState())
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
    ) {
        Text(
            text = stringResource(Res.string.create_account_title),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(vertical = 35.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )

        Text(
            text = stringResource(Res.string.sign_up_subtitle),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(vertical = 35.dp)
        )

        SectionHeader(
            text = stringResource(Res.string.name_label),
            icon = Icons.Default.Person
        )
        MyOutLinedTextField(
            text = state.user,
            label = stringResource(Res.string.full_name_placeholder),
            onValueChange = { vm.updateParameterStatus(user = it) },
            modifier = Modifier.fillMaxWidth()
        )
        SectionHeader(
            text = stringResource(Res.string.email_label),
            icon = Icons.Default.Email,
            modifier = Modifier.padding(bottom = 5.dp, top = 25.dp)
        )
        MyOutLinedTextField(
            text = state.email,
            label = stringResource(Res.string.email_placeholder),
            onValueChange = { vm.updateParameterStatus(email = it) },
            modifier = Modifier.fillMaxWidth()
        )
        SectionHeader(
            text = stringResource(Res.string.password_label),
            icon = Icons.Default.Lock,
            modifier = Modifier.padding(bottom = 5.dp, top = 25.dp)
        )
        MyOutLinedTextField(
            text = state.password,
            label = stringResource(Res.string.password_placeholder),
            onValueChange = { vm.updateParameterStatus(password = it) },
            isPasswordField = true, modifier = Modifier.fillMaxWidth()
        )

        MyButton(
            text = stringResource(Res.string.create_account_button),
            enabled = true,
            onClick = { vm.createAccount() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .fillMaxWidth().padding(bottom = 30.dp, top = 30.dp)
        )

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(Res.string.already_have_account_text))
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(Res.string.sign_in_link),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable(onClick = { onSignIn() })
            )
        }
        MyAlertDialog(
            showDialog = state.showDialog,
            title = getTitleAlertDialog(error = state.createAccountError),
            text = getErrorMessage(error = state.createAccountError),
            alertType = getAlertType(error = state.createAccountError),
            onDismiss = { vm.onDialogDismiss() },
            onConfirm = { vm.onDialogDismiss() }
        )
    }
}

@Composable
private fun getErrorMessage(error: CreateAccountError): String {
    return when (error) {
        CreateAccountError.EMPTY_FIELDS -> stringResource(Res.string.empty_fields_error)
        CreateAccountError.ACCOUNT_CREATED -> stringResource(Res.string.account_created_successfully)
        CreateAccountError.ACCOUNT_CREATED_ERROR -> stringResource(Res.string.error_creating_account)
        CreateAccountError.OK -> STRING_EMPTY
    }
}

@Composable
private fun getTitleAlertDialog(error: CreateAccountError): String {
    return when (error) {
        CreateAccountError.EMPTY_FIELDS -> stringResource(Res.string.error_title)
        CreateAccountError.ACCOUNT_CREATED -> stringResource(Res.string.success_title)
        CreateAccountError.ACCOUNT_CREATED_ERROR -> stringResource(Res.string.error_title)
        CreateAccountError.OK -> STRING_EMPTY
    }
}

@Composable
private fun getAlertType(error: CreateAccountError): AlertType {
    return when (error) {
        CreateAccountError.EMPTY_FIELDS -> AlertType.ERROR
        CreateAccountError.ACCOUNT_CREATED -> AlertType.SUCCESS
        CreateAccountError.ACCOUNT_CREATED_ERROR -> AlertType.ERROR
        CreateAccountError.OK -> AlertType.INFO
    }
}