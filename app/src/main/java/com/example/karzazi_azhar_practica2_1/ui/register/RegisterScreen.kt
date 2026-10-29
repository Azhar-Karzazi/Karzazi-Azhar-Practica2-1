package com.example.karzazi_azhar_practica2_1.ui.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.karzazi_azhar_practica2_1.ui.components.AppTextField
import com.example.karzazi_azhar_practica2_1.ui.components.PasswordField
import com.example.karzazi_azhar_practica2_1.ui.components.PizzeriaLogo
import com.example.karzazi_azhar_practica2_1.ui.theme.KarzaziAzharPractica21Theme

// Pantalla "con estado": conecta el ViewModel con la UI
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    RegisterContent(
        uiState = uiState,
        onNombreChange = viewModel::onNombreChange,
        onDniChange = viewModel::onDniChange,
        onDireccionChange = viewModel::onDireccionChange,
        onTelefonoChange = viewModel::onTelefonoChange,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onConfirmPasswordChange = viewModel::onConfirmPasswordChange,
        onRegisterClick = viewModel::onRegisterClick,
        modifier = modifier
    )
}

// Pantalla "sin estado": solo pinta lo que recibe (state hoisting)
@Composable
fun RegisterContent(
    uiState: RegisterUiState,
    onNombreChange: (String) -> Unit,
    onDniChange: (String) -> Unit,
    onDireccionChange: (String) -> Unit,
    onTelefonoChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        PizzeriaLogo()
        Spacer(modifier = Modifier.height(16.dp))

        AppTextField(
            value = uiState.nombre,
            onValueChange = onNombreChange,
            label = "Nombre",
            error = uiState.errorNombre
        )

        AppTextField(
            value = uiState.dni,
            onValueChange = onDniChange,
            label = "DNI",
            error = uiState.errorDni
        )

        AppTextField(
            value = uiState.direccion,
            onValueChange = onDireccionChange,
            label = "Dirección"
        )

        AppTextField(
            value = uiState.telefono,
            onValueChange = onTelefonoChange,
            label = "Teléfono",
            keyboardType = KeyboardType.Phone
        )

        AppTextField(
            value = uiState.email,
            onValueChange = onEmailChange,
            label = "Email",
            keyboardType = KeyboardType.Email,
            error = uiState.errorEmail
        )

        PasswordField(
            value = uiState.password,
            onValueChange = onPasswordChange,
            label = "Contraseña",
            error = uiState.errorPassword
        )

        PasswordField(
            value = uiState.confirmPassword,
            onValueChange = onConfirmPasswordChange,
            label = "Confirmar contraseña",
            imeAction = ImeAction.Done,
            error = uiState.errorConfirmPassword
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onRegisterClick,
            enabled = uiState.botonHabilitado,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrarse")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterContentPreview() {
    KarzaziAzharPractica21Theme {
        RegisterContent(
            uiState = RegisterUiState(),
            onNombreChange = {},
            onDniChange = {},
            onDireccionChange = {},
            onTelefonoChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onConfirmPasswordChange = {},
            onRegisterClick = {}
        )
    }
}
