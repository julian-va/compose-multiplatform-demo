package jva.cloud.democomposemultiplatform.presentation.views.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import jva.cloud.democomposemultiplatform.presentation.viewmodel.login.LoginViewModel
import jva.cloud.democomposemultiplatform.presentation.viewmodel.login.UiState
import kotlinx.serialization.Serializable

@Serializable
object Login

@Composable
fun Login(viewModel: LoginViewModel = viewModel { LoginViewModel() }) {
    val state: UiState = viewModel.state
    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            8.dp,
            Alignment.CenterVertically
        )
    ) {
        MyOutLinedTextField(
            text = user,
            label = "user",
            isError = state.error,
            onValueChange = { user = it })
        MyOutLinedTextField(
            text = password,
            label = "password",
            isError = state.error,
            onValueChange = { password = it })
        MyButton(
            text = "Login",
            enabled = state.enabledButtonLogin,
            onClick = { viewModel.login(user = user, password = password) })
        if (state.message.isNotEmpty()) {
            Text(text = state.message)
        }
    }
}

@Composable
private fun MyOutLinedTextField(
    text: String,
    label: String,
    isError: Boolean,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = text,
        label = { Text(label) },
        isError = isError,
        onValueChange = onValueChange
    )
}

@Composable
private fun MyButton(text: String, enabled: Boolean, onClick: () -> Unit) {
    Button(enabled = enabled, onClick = { onClick() }) {
        Text(text = text)
    }
}