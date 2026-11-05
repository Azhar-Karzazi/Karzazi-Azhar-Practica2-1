package com.example.karzazi_azhar_practica2_1.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

/**
 * Botones "-  1  +". No guarda la cantidad: la recibe y avisa con onMas / onMenos
 * (state hoisting).
 */
@Composable
fun SelectorCantidad(
    cantidad: Int,
    onMas: () -> Unit,
    onMenos: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(onClick = onMenos) {
            Text("-")
        }
        Text(text = "$cantidad", fontSize = 18.sp)
        TextButton(onClick = onMas) {
            Text("+")
        }
    }
}
