package jva.cloud.democomposemultiplatform.presentation.viewmodel.login

data class UiState(
    val loggedIn: Boolean = false,
    val error: Boolean = false,
    val message: String = "",
    val enabledButtonLogin: Boolean = true
)