package jva.cloud.democomposemultiplatform.presentation.viewmodel.createaccount

import jva.cloud.democomposemultiplatform.utils.ConstantApp.STRING_EMPTY

data class CreateAccountViewModelState(
    val showDialog: Boolean = false,
    val isLoading: Boolean = false,
    val createAccountError: CreateAccountError = CreateAccountError.OK,
    val enabledButtonLogin: Boolean = true,
    val user: String = STRING_EMPTY,
    val password: String = STRING_EMPTY,
    val email: String = STRING_EMPTY
)
