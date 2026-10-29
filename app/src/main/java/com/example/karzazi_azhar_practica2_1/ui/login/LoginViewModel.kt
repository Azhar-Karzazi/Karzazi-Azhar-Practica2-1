package com.example.karzazi_azhar_practica2_1.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.karzazi_azhar_practica2_1.data.LoginDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onEmailChange(nuevoEmail: String) {
        _uiState.update { it.copy(email = nuevoEmail) }
    }

    fun onPasswordChange(nuevaPassword: String) {
        _uiState.update { it.copy(password = nuevaPassword) }
    }

    fun onLoginClick() {
        val loginDTO = LoginDTO(
            email = _uiState.value.email,
            password = _uiState.value.password
        )
        Log.i("LoginViewModel", loginDTO.toString())
    }
}
