package com.example.codehub.presentation.auth.login

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel {
    private val _uiState = MutableStateFlow(LoginUiState());
    val uiState = _uiState.asStateFlow();

    fun onEmailChanged(newEmail: String) {
        _uiState.update { currentState ->
            currentState.copy(
                email = newEmail,
                emailError = null
            )
        }
    }

    fun onPasswordChanged(newPassword: String) {
    _uiState.update { currentState ->
        currentState.copy(
                password = newPassword,
                passwordError = null
            )
        }
    }

    fun onPasswordVisibilityChanged() {
        _uiState.update { currentState ->
            currentState.copy(
                isPasswordVisible = !currentState.isPasswordVisible
            )
        }
    }

    fun
}