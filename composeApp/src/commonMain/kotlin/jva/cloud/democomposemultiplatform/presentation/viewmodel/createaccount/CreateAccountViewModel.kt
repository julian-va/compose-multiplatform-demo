package jva.cloud.democomposemultiplatform.presentation.viewmodel.createaccount

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jva.cloud.democomposemultiplatform.domain.model.CreatedUser
import jva.cloud.democomposemultiplatform.domain.model.User
import jva.cloud.democomposemultiplatform.domain.usecase.CreateUser
import jva.cloud.democomposemultiplatform.utils.ConstantApp.DEFAULT_AVATAR_URL
import jva.cloud.democomposemultiplatform.utils.ConstantApp.ONE
import jva.cloud.democomposemultiplatform.utils.ConstantApp.STRING_EMPTY
import kotlinx.coroutines.launch

class CreateAccountViewModel(private val createUser: CreateUser) : ViewModel() {
    var state by mutableStateOf(CreateAccountViewModelState())
        private set

    fun createAccount() {
        if (validParameters(
                user = state.user,
                password = state.password,
                email = state.email
            )
        ) {
            state = state.copy(createAccountError = CreateAccountError.EMPTY_FIELDS)
            openDialog()
            return
        }
        performAccountCreation()
    }

    private fun performAccountCreation() {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            val result: Result<CreatedUser> =
                createUser.createUser(
                    user = User(
                        name = state.user,
                        email = state.email,
                        password = state.password,
                        avatar = DEFAULT_AVATAR_URL
                    )
                )
            result.onSuccess {
                state = state.copy(
                    isLoading = false,
                    createAccountError = CreateAccountError.ACCOUNT_CREATED
                )
                openDialog()
                clearFields()
            }.onFailure {
                state =
                    state.copy(
                        isLoading = false,
                        createAccountError = CreateAccountError.ACCOUNT_CREATED_ERROR
                    )
                openDialog()
            }
        }
    }

    fun updateParameterStatus(
        user: String = STRING_EMPTY,
        password: String = STRING_EMPTY,
        email: String = STRING_EMPTY
    ) {
        state = state.copy(
            user = updateUserAttributeField(user, state.user),
            password = updateUserAttributeField(password, state.password),
            email = updateUserAttributeField(email, state.email)
        )
    }

    private fun validParameters(user: String, password: String, email: String): Boolean {
        return user.isBlank() || password.isBlank() || email.isBlank()
    }

    private fun updateUserAttributeField(valueNew: String, valueOld: String): String {
        val setValueNew: Boolean = valueNew.isNotEmpty() || valueOld.length <= ONE
        return if (setValueNew) valueNew else valueOld
    }

    private fun clearFields() {
        state = state.copy(
            user = STRING_EMPTY,
            password = STRING_EMPTY,
            email = STRING_EMPTY
        )
    }

    private fun openDialog() {
        dialogState(show = true)
    }

    fun onDialogDismiss() {
        dialogState(show = false, error = CreateAccountError.OK)
    }

    private fun dialogState(show: Boolean, error: CreateAccountError = state.createAccountError) {
        state = state.copy(showDialog = show, createAccountError = error)
    }
}