package jva.cloud.democomposemultiplatform.presentation.views.createaccount

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import democomposemultiplatform.composeapp.generated.resources.Res
import democomposemultiplatform.composeapp.generated.resources.already_have_account_text
import democomposemultiplatform.composeapp.generated.resources.create_account_button
import democomposemultiplatform.composeapp.generated.resources.create_account_title
import democomposemultiplatform.composeapp.generated.resources.email_label
import democomposemultiplatform.composeapp.generated.resources.email_placeholder
import democomposemultiplatform.composeapp.generated.resources.full_name_placeholder
import democomposemultiplatform.composeapp.generated.resources.name_label
import democomposemultiplatform.composeapp.generated.resources.password_label
import democomposemultiplatform.composeapp.generated.resources.password_placeholder
import democomposemultiplatform.composeapp.generated.resources.sign_in_link
import democomposemultiplatform.composeapp.generated.resources.sign_up_subtitle
import jva.cloud.democomposemultiplatform.presentation.components.MyOutLinedTextField
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Serializable
object CreateAccount

@Composable
fun CreateAccountView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {
            Text(
                text = stringResource(Res.string.create_account_title),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(vertical = 35.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            )

            Text(
                text = stringResource(Res.string.sign_up_subtitle),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(vertical = 35.dp)
            )

            Row(
                modifier = Modifier.padding(bottom = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Person, contentDescription = null)
                Text(text = stringResource(Res.string.name_label))
            }
            MyOutLinedTextField(
                text = "",
                label = stringResource(Res.string.full_name_placeholder),
                onValueChange = {},
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.padding(bottom = 5.dp, top = 25.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Email, contentDescription = null)
                Text(text = stringResource(Res.string.email_label))
            }
            MyOutLinedTextField(
                text = "",
                label = stringResource(Res.string.email_placeholder),
                onValueChange = {},
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.padding(bottom = 5.dp, top = 25.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Lock, contentDescription = null)
                Text(text = stringResource(Res.string.password_label))
            }
            MyOutLinedTextField(
                text = "",
                label = stringResource(Res.string.password_placeholder),
                onValueChange = {},
                isPasswordField = true, modifier = Modifier.fillMaxWidth()
            )

            MyButton(
                text = stringResource(Res.string.create_account_button), enabled = true, onClick = {},
                modifier = Modifier.align(Alignment.CenterHorizontally)
                    .fillMaxWidth().padding(bottom = 30.dp, top = 30.dp)
            )

            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(Res.string.already_have_account_text))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = stringResource(Res.string.sign_in_link), color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}

@Composable
private fun MyButton(
    text: String,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        enabled = enabled,
        onClick = { onClick() },
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun CreateAccountViewPreview() {
    CreateAccountView()
}
