package com.example.karzazi_azhar_practica2_1.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Fila de tallas. La talla elegida se ve como botón con fondo; el resto, solo texto.
 * No guarda la talla: la recibe y avisa con onTallaChange (state hoisting).
 */
@Composable
fun SelectorTalla(
    tallas: List<String>,
    tallaSeleccionada: String?,
    onTallaChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        tallas.forEach { talla ->
            if (talla == tallaSeleccionada) {
                Button(onClick = { onTallaChange(talla) }) {
                    Text(talla)
                }
            } else {
                TextButton(onClick = { onTallaChange(talla) }) {
                    Text(talla)
                }
            }
        }
    }
}
