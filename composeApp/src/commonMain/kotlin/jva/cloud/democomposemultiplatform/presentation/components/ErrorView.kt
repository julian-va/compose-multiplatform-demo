package jva.cloud.democomposemultiplatform.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import democomposemultiplatform.composeapp.generated.resources.Res
import democomposemultiplatform.composeapp.generated.resources.error_view_default_error
import democomposemultiplatform.composeapp.generated.resources.error_view_preview_error_1
import democomposemultiplatform.composeapp.generated.resources.error_view_preview_error_2
import democomposemultiplatform.composeapp.generated.resources.try_again
import democomposemultiplatform.composeapp.generated.resources.warning_icon
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    error: String? = null,
    onRetry: (() -> Unit)? = null
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = stringResource(Res.string.warning_icon),
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = error ?: stringResource(Res.string.error_view_default_error),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            if (onRetry != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onRetry) {
                    Text(text = stringResource(Res.string.try_again))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorViewPreview() {
    ErrorView(
        error = stringResource(Res.string.error_view_preview_error_1),
        onRetry = {}
    )
}

@Preview(showBackground = true)
@Composable
fun ErrorViewPreviewWithoutRetry() {
    ErrorView(
        error = stringResource(Res.string.error_view_preview_error_2)
    )
}
