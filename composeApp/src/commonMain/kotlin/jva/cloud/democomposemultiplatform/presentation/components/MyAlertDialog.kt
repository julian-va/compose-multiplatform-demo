package jva.cloud.democomposemultiplatform.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import democomposemultiplatform.composeapp.generated.resources.Res
import democomposemultiplatform.composeapp.generated.resources.cancel_button_text
import democomposemultiplatform.composeapp.generated.resources.info_icon_content_description
import democomposemultiplatform.composeapp.generated.resources.ok_button_text
import jva.cloud.democomposemultiplatform.presentation.components.model.AlertType
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun MyAlertDialog(
    showDialog: Boolean,
    title: String,
    text: String,
    alertType: AlertType,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (showDialog) {
        val icon: ImageVector = getIcon(alertType)

        AlertDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(text = stringResource(Res.string.ok_button_text))
                }
            },
            dismissButton = {
                if (alertType != AlertType.SUCCESS) {
                    TextButton(onClick = { onDismiss() }) {
                        Text(text = stringResource(Res.string.cancel_button_text))
                    }
                }
            },
            title = { Text(text = title) },
            text = { Text(text = text) },
            icon = {
                Icon(
                    imageVector = icon,
                    contentDescription = stringResource(Res.string.info_icon_content_description)
                )
            }
        )
    }
}

private fun getIcon(alertType: AlertType): ImageVector {
    return when (alertType) {
        AlertType.SUCCESS -> Icons.Default.CheckCircle
        AlertType.ERROR -> Icons.Default.Error
        AlertType.INFO -> Icons.Default.Info
    }
}
