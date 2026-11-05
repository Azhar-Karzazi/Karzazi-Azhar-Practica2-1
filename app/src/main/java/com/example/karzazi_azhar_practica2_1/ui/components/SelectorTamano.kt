package com.example.karzazi_azhar_practica2_1.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.karzazi_azhar_practica2_1.data.Tamano

/**
 * Fila con los tamaños. El elegido se ve como botón con fondo; el resto, solo texto.
 * No guarda el tamaño: lo recibe y avisa con onTamanoChange (state hoisting).
 */
@Composable
fun SelectorTamano(
    tamanoSeleccionado: Tamano?,
    onTamanoChange: (Tamano) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Tamano.entries.forEach { tamano ->
            if (tamano == tamanoSeleccionado) {
                Button(onClick = { onTamanoChange(tamano) }) {
                    Text(tamano.etiqueta)
                }
            } else {
                TextButton(onClick = { onTamanoChange(tamano) }) {
                    Text(tamano.etiqueta)
                }
            }
        }
    }
}
