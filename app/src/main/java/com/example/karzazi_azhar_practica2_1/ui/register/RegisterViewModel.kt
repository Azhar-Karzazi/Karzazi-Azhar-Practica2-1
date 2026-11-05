package com.example.karzazi_azhar_practica2_1.ui.register

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.karzazi_azhar_practica2_1.model.UsuarioDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState

    fun onNombreChange(nuevoNombre: String) {
        _uiState.update { it.copy(nombre = nuevoNombre) }
    }

    fun onDniChange(nuevoDni: String) {
        _uiState.update { it.copy(dni = nuevoDni.uppercase()) }
    }

    fun onDireccionChange(nuevaDireccion: String) {
        _uiState.update { it.copy(direccion = nuevaDireccion) }
    }

    fun onTelefonoChange(nuevoTelefono: String) {
        _uiState.update { it.copy(telefono = nuevoTelefono) }
    }

    fun onEmailChange(nuevoEmail: String) {
        _uiState.update { it.copy(email = nuevoEmail) }
    }

    fun onPasswordChange(nuevaPassword: String) {
        _uiState.update { it.copy(password = nuevaPassword) }
    }

    fun onConfirmPasswordChange(nuevaConfirmacion: String) {
        _uiState.update { it.copy(confirmPassword = nuevaConfirmacion) }
    }

    fun onRegisterClick() {
        val estado = _uiState.value
        val usuarioDTO = UsuarioDTO(
            nombre = estado.nombre,
            dni = estado.dni,
            direccion = estado.direccion,
            telefono = estado.telefono,
            email = estado.email,
            password = estado.password
        )
        Log.i("RegisterViewModel", usuarioDTO.toString())
    }
}
