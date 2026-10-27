package com.example.codehub.presentation.auth.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.codehub.R
import com.example.codehub.presentation.components.TopBar
import com.example.codehub.presentation.components.buttons.PrimaryButton
import com.example.codehub.presentation.components.buttons.SecondaryButton
import com.example.codehub.presentation.components.fields.FormTextField
import com.example.codehub.presentation.components.fields.PasswordField
import com.example.codehub.presentation.theme.BorderGray
import com.example.codehub.presentation.theme.LightGray
import com.example.codehub.presentation.theme.RedOrange
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel()
) {
    val uiState by loginViewModel.uiState.collectAsStateWithLifecycle()

    LoginContent(
        uiState = uiState,
        onEmailChanged = loginViewModel::onEmailChanged,
        onPasswordChanged = loginViewModel::onPasswordChanged,
        onPasswordVisibilityChanged = loginViewModel::onPasswordVisibilityChanged
    )
}

@Composable
private fun LoginContent(
    uiState: LoginUiState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordVisibilityChanged: () -> Unit
) {
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

        LoginCard(
            uiState = uiState,
            onEmailChanged = onEmailChanged,
            onPasswordChanged = onPasswordChanged,
            onPasswordVisibilityChanged = onPasswordVisibilityChanged,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        CreateAccountSection(
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
private fun LoginCard(
    uiState: LoginUiState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordVisibilityChanged: () -> Unit,
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
                text = stringResource(R.string.login_title),
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = stringResource(R.string.login_subtitle),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            SecondaryButton(
                text = stringResource(R.string.login_button_google),
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                icon = painterResource(R.drawable.ic_google),
                iconContentDescription = null
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            LoginDivider()

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            FormTextField(
                value = uiState.email,
                onValueChange = onEmailChanged,
                label = stringResource(R.string.login_label_email),
                placeholder = stringResource(
                    R.string.login_placeholder_email
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            PasswordField(
                value = uiState.password,
                onValueChange = onPasswordChanged,
                isPasswordVisible = uiState.isPasswordVisible,
                onPasswordVisibilityChanged = onPasswordVisibilityChanged,
                label = stringResource(R.string.login_label_password),
                placeholder = stringResource(
                    R.string.login_placeholder_password
                ),
                modifier = Modifier.fillMaxWidth()
            )

            ForgotPasswordButton(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            PrimaryButton(
                text = stringResource(R.string.login_button),
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = stringResource(R.string.login_terms),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun LoginDivider() {
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
            text = stringResource(R.string.login_divider),
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
private fun ForgotPasswordButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterEnd
    ) {
        TextButton(
            onClick = onClick
        ) {
            Text(
                text = stringResource(
                    R.string.login_forgot_password
                ),
                style = MaterialTheme.typography.labelLarge,
                color = RedOrange
            )
        }
    }
}

@Composable
private fun CreateAccountSection(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(
                R.string.login_no_account
            ),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        TextButton(
            onClick = {}
        ) {
            Text(
                text = stringResource(
                    R.string.login_create_account
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
private fun LoginScreenPreview() {
        LoginScreen()
}