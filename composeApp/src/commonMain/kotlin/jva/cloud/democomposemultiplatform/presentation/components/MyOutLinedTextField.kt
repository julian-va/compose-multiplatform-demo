package jva.cloud.democomposemultiplatform.presentation.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import democomposemultiplatform.composeapp.generated.resources.Res
import democomposemultiplatform.composeapp.generated.resources.hide_password_content_description
import democomposemultiplatform.composeapp.generated.resources.show_password_content_description
import org.jetbrains.compose.resources.stringResource


@Composable
internal fun MyOutLinedTextField(
    text: String,
    label: String, onValueChange: (String) -> Unit,
    isPasswordField: Boolean = false,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    modifier: Modifier = Modifier
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier,
        value = text,
        label = { Text(label) },
        onValueChange = onValueChange,
        isError = isError,
        singleLine = true,
        visualTransformation = getVisualTransformation(
            isPasswordField = isPasswordField,
            passwordVisible = passwordVisible
        ),
        keyboardOptions = getKeyboardOptions(
            isPasswordField = isPasswordField,
            defaultOptions = keyboardOptions
        ),
        trailingIcon = {
            PasswordVisibilityIcon(
                isPasswordField = isPasswordField,
                passwordVisible = passwordVisible,
                onToggleVisibility = { passwordVisible = !passwordVisible }
            )
        }
    )
}

/**
 * Determines the VisualTransformation for the text field based on whether it's a password field and if the password is visible.
 */
private fun getVisualTransformation(
    isPasswordField: Boolean,
    passwordVisible: Boolean
): VisualTransformation {
    return if (isPasswordField && !passwordVisible) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }
}

/**
 * Determines the KeyboardOptions for the text field based on whether it's a password field.
 */
private fun getKeyboardOptions(
    isPasswordField: Boolean,
    defaultOptions: KeyboardOptions
): KeyboardOptions {
    return if (isPasswordField) {
        KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done)
    } else {
        defaultOptions
    }
}

/**
 * Displays a trailing icon to toggle password visibility if the text field is a password field.
 */
@Composable
private fun PasswordVisibilityIcon(
    isPasswordField: Boolean,
    passwordVisible: Boolean,
    onToggleVisibility: () -> Unit
) {
    if (isPasswordField) {
        val image = if (passwordVisible) {
            Icons.Filled.Visibility
        } else {
            Icons.Filled.VisibilityOff
        }

        val description = if (passwordVisible) {
            stringResource(Res.string.hide_password_content_description)
        } else {
            stringResource(Res.string.show_password_content_description)
        }

        IconButton(onClick = onToggleVisibility) {
            Icon(imageVector = image, contentDescription = description)
        }
    }
}
