package com.example.karzazi_azhar_practica2_1.ui.register

import android.util.Patterns

data class RegisterUiState(
    val nombre: String = "",
    val dni: String = "",
    val direccion: String = "",
    val telefono: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = ""
) {

    // Cada error es null si el campo está vacío (aún no se ha escrito) o si es correcto
    val errorNombre: String?
        get() = if (nombre.any { it.isDigit() }) {
            "El nombre no puede contener dígitos"
        } else null

    val errorDni: String?
        get() = when {
            dni.isEmpty() -> null
            dni.length != 9 || !dni.take(8).all { it.isDigit() } || !dni.last().isLetter() ->
                "El DNI debe tener 8 números y una letra"
            dni.last().uppercaseChar() != letraDni(dni.take(8).toInt()) ->
                "La letra del DNI no es correcta"
            else -> null
        }

    val errorEmail: String?
        get() = if (email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            "El email no tiene un formato válido"
        } else null

    val errorPassword: String?
        get() = if (password.isNotEmpty() && password.length < 4) {
            "La contraseña debe tener al menos 4 caracteres"
        } else null

    val errorConfirmPassword: String?
        get() = if (confirmPassword.isNotEmpty() && confirmPassword != password) {
            "Las contraseñas no coinciden"
        } else null

    // El botón solo se habilita con todo relleno y sin ningún error
    val botonHabilitado: Boolean
        get() = nombre.isNotBlank() &&
                dni.isNotBlank() &&
                direccion.isNotBlank() &&
                telefono.isNotBlank() &&
                email.isNotBlank() &&
                password.isNotBlank() &&
                confirmPassword.isNotBlank() &&
                errorNombre == null &&
                errorDni == null &&
                errorEmail == null &&
                errorPassword == null &&
                errorConfirmPassword == null

    private fun letraDni(numero: Int): Char {
        val letras = "TRWAGMYFPDXBNJZSQVHLCKE"
        return letras[numero % 23]
    }
}
