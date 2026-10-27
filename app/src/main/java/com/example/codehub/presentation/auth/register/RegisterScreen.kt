package com.example.codehub.presentation.auth.register

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.codehub.R
import com.example.codehub.presentation.components.TopBar
import com.example.codehub.presentation.components.buttons.PrimaryButton
import com.example.codehub.presentation.components.buttons.SecondaryButton
import com.example.codehub.presentation.components.fields.FormTextField
import com.example.codehub.presentation.components.fields.PasswordField
import com.example.codehub.presentation.theme.BorderGray
import com.example.codehub.presentation.theme.LightGray
import com.example.ds.designsystem.theme.RedOrange

@Composable
fun RegisterScreen() {
    RegisterContent()
}

@Composable
private fun RegisterContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray)
            .verticalScroll(rememberScrollState())
    ) {
        TopBar(
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        RegisterCard(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        LoginSection(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )
    }
}

@Composable
private fun RegisterCard(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(
            width = 1.dp,
            color = BorderGray
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = stringResource(R.string.register_title),
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = stringResource(R.string.register_subtitle),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray

            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            SecondaryButton(
                text = stringResource(
                    R.string.register_button_google
                ),
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                icon = painterResource(R.drawable.ic_google),
                iconContentDescription = null
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            RegisterDivider()

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            FormTextField(
                value = "",
                onValueChange = {},
                label = stringResource(
                    R.string.register_label_name
                ),
                placeholder = stringResource(
                    R.string.register_placeholder_name
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            FormTextField(
                value = "",
                onValueChange = {},
                label = stringResource(
                    R.string.register_label_email
                ),
                placeholder = stringResource(
                    R.string.register_placeholder_email
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            PasswordField(
                value = "",
                onValueChange = {},
                label = stringResource(
                    R.string.register_label_password
                ),
                placeholder = stringResource(
                    R.string.register_placeholder_password
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            PasswordField(
                value = "",
                onValueChange = {},
                label = stringResource(
                    R.string.register_label_confirm_password
                ),
                placeholder = stringResource(
                    R.string.register_placeholder_confirm_password
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = stringResource(
                    R.string.register_password_hint
                ),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            TermsCheckbox(
                checked = false,
                onCheckedChange = {}
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            PrimaryButton(
                text = stringResource(
                    R.string.register_button
                ),
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun RegisterDivider() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = Color.Gray
        )

        Text(
            text = stringResource(R.string.register_divider),
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )

        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = Color.Gray

        )
    }
}

@Composable
private fun TermsCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )

        Text(
            text = stringResource(R.string.register_terms),
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
    }
}

@Composable
private fun LoginSection(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(
                R.string.register_already_account
            ),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        TextButton(
            onClick = {}
        ) {
            Text(
                text = stringResource(
                    R.string.register_login
                ),
                style = MaterialTheme.typography.labelLarge,
                color = RedOrange
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun RegisterScreenPreview() {
        RegisterScreen()
}