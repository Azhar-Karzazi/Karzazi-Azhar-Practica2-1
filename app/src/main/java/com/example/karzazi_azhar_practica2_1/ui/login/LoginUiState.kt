package com.example.karzazi_azhar_practica2_1.ui.login

data class LoginUiState(
    val email: String = "",
    val password: String = ""
) {
    // El boton se habilita solo si los dos campos están rellenos
    val botonHabilitado: Boolean
            get() = email.isNotBlank() && password.isNotBlank()
}