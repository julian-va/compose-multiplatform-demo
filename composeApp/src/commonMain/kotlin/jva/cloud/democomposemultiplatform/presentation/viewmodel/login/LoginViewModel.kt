package jva.cloud.democomposemultiplatform.presentation.viewmodel.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jva.cloud.democomposemultiplatform.domain.model.Credentials
import jva.cloud.democomposemultiplatform.domain.model.Token
import jva.cloud.democomposemultiplatform.domain.usecase.RememberSession
import jva.cloud.democomposemultiplatform.domain.usecase.SignInUser
import jva.cloud.democomposemultiplatform.utils.ConstantApp.ONE
import jva.cloud.democomposemultiplatform.utils.ConstantApp.STRING_EMPTY
import kotlinx.coroutines.launch

class LoginViewModel(
    private val signInUser: SignInUser,
    private val rememberSession: RememberSession
) : ViewModel() {
    var state by mutableStateOf(LoginViewModelState())
        private set

    init {
        viewModelScope.launch {
            val rememberSession: Boolean = rememberSession.getRememberSession()
            state = state.copy(rememberMe = rememberSession, loggedIn = rememberSession)
        }
    }

    fun login() {

        if (validParameters(user = state.user, password = state.password)) {
            state = state.copy(loginError = LoginError.EMPTY_FIELDS)
            openDialog()
            return
        }

        performLogin(user = state.user, password = state.password)
    }

    private fun performLogin(user: String, password: String) {
        state = state.copy(isLoading = true)

        viewModelScope.launch {
            val result: Result<Token> =
                signInUser(credentials = Credentials(email = user, password = password))

            result.onSuccess {
                state = state.copy(loggedIn = true, isLoading = false)
                rememberSession.setRememberSession(remember = state.rememberMe)
            }.onFailure {
                state = state.copy(isLoading = false, loginError = LoginError.INVALID_CREDENTIALS)
                openDialog()
            }
        }
    }

    fun updateParameterStatus(user: String = STRING_EMPTY, password: String = STRING_EMPTY) {
        state = state.copy(
            user = updateUserAttributeField(user, state.user),
            password = updateUserAttributeField(password, state.password),
        )
    }

    fun updateRememberMeStatus() {
        state = state.copy(rememberMe = !state.rememberMe)
    }

    private fun updateUserAttributeField(valueNew: String, valueOld: String): String {
        val setValueNew: Boolean = valueNew.isNotEmpty() || valueOld.length <= ONE
        return if (setValueNew) valueNew else valueOld
    }

    private fun validParameters(user: String, password: String): Boolean {
        return user.isBlank() || password.isBlank()
    }


    private fun openDialog() {
        dialogState(show = true)
    }

    fun onDialogDismiss() {
        dialogState(show = false, error = null)
    }

    private fun dialogState(show: Boolean, error: LoginError? = state.loginError) {
        state = state.copy(showDialog = show, loginError = error)
    }
}
