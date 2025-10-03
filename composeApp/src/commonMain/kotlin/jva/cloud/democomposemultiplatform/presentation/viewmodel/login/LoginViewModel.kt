package jva.cloud.democomposemultiplatform.presentation.viewmodel.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var state by mutableStateOf(UiState())
        private set

    fun login(user: String, password: String) {
        validParameters(user = user, password = password)
    }

    private fun validParameters(user: String, password: String) {
        state = when {
            !user.contains("@") -> UiState(message = "Invalid User", error = true)
            password.length < 6 -> UiState(message = "Invalid Password", error = true)
            user.isBlank() || password.isBlank() -> UiState(enabledButtonLogin = false)
            else -> UiState(loggedIn = true, message = "Success")
        }
    }

    fun validFiel(user: String, password: String) {
        state = when {
            user.isBlank() || password.isBlank() -> UiState(enabledButtonLogin = false)
            else -> UiState()
        }
    }
}