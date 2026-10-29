package com.example.karzazi_azhar_practica2_1.ui.login

data class LoginUiState(
    val email: String = "",
    val password: String = ""
) {
    // El botón solo se habilita si los dos campos están rellenos
    val botonHabilitado: Boolean
        get() = email.isNotBlank() && password.isNotBlank()
}
