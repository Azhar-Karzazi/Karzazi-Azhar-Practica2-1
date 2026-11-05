package com.example.karzazi_azhar_practica2_1.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.karzazi_azhar_practica2_1.model.LineaPedido

/**
 * "(-) [ 2 ] (+)". La cantidad se puede cambiar con los botones o escribiéndola:
 * el campo usa el teclado numérico (KeyboardType.Number) porque el dato es un número.
 *
 * No guarda la cantidad: la recibe y avisa con [onCantidadChange] (state hoisting).
 */
@Composable
fun SelectorCantidad(
    cantidad: Int,
    onCantidadChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    cantidadMaxima: Int = LineaPedido.CANTIDAD_MAXIMA
) {
    // Para cerrar el teclado al pulsar "Done"
    val focusManager = LocalFocusManager.current

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { onCantidadChange(cantidad - 1) },
            enabled = cantidad > 0,
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Icon(imageVector = Icons.Filled.Remove, contentDescription = "Quitar uno")
        }

        OutlinedTextField(
            value = cantidad.toString(),
            onValueChange = { texto -> onCantidadChange(textoACantidad(texto, cantidad)) },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            modifier = Modifier.width(60.dp)
        )

        IconButton(
            onClick = { onCantidadChange(cantidad + 1) },
            enabled = cantidad < cantidadMaxima,
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Añadir uno")
        }
    }
}

// Convierte lo que escribe el usuario en un número válido: solo cifras y como mucho 2.
private fun textoACantidad(texto: String, cantidadActual: Int): Int {
    var cifras = texto.filter { it.isDigit() }
    // Si había un 0 y el usuario escribe otra cifra, quitamos ese 0 ("05" o "50" -> 5)
    if (cantidadActual == 0 && cifras.length > 1) {
        cifras = cifras.replaceFirst("0", "")
    }
    return cifras.take(2).toIntOrNull() ?: 0
}
