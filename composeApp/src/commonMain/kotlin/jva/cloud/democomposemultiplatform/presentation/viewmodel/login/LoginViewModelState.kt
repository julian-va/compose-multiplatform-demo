package jva.cloud.democomposemultiplatform.presentation.viewmodel.login

import jva.cloud.democomposemultiplatform.utils.ConstantApp.STRING_EMPTY

data class LoginViewModelState(
    val showDialog: Boolean = false,
    val isLoading: Boolean = false,
    val loggedIn: Boolean = false,
    val loginError: LoginError? = null,
    val enabledButtonLogin: Boolean = true,
    val user: String = STRING_EMPTY,
    val password: String = STRING_EMPTY,
    val rememberMe: Boolean = false
)
