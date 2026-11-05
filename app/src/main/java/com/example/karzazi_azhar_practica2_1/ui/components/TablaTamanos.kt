package com.example.karzazi_azhar_practica2_1.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.karzazi_azhar_practica2_1.model.Producto
import com.example.karzazi_azhar_practica2_1.model.Tamano

// Ancho de la columna "Cantidad" (2 IconButton de 48dp + campo de 60dp)
private val AnchoColumnaCantidad = 156.dp

/**
 * Tabla de un producto con una fila por tamaño: Tamaño | Precio | Cantidad.
 * Si el producto no tiene tamaños (la pasta), tiene una sola fila "Ración".
 *
 * Cada fila tiene su propia cantidad, así se puede pedir la misma pizza en varios
 * tamaños a la vez (por ejemplo, 2 medianas y 1 grande).
 *
 * No guarda estado: recibe las cantidades y avisa con [onCantidadChange] (state hoisting).
 */
@Composable
fun TablaTamanos(
    producto: Producto,
    cantidades: Map<Tamano?, Int>,
    onCantidadChange: (Tamano?, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.colorScheme.surfaceContainerLow,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = modifier.fillMaxWidth()
    ) {
        Column {
            // Cabecera de la tabla
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                    .padding(start = 12.dp, top = 8.dp, bottom = 8.dp)
            ) {
                TextoCabecera(texto = "Tamaño", modifier = Modifier.weight(1f))
                TextoCabecera(texto = "Precio", modifier = Modifier.weight(1f))
                TextoCabecera(
                    texto = "Cantidad",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(AnchoColumnaCantidad)
                )
            }

            // Una fila por cada tamaño
            producto.opcionesTamano.forEach { tamano ->
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                FilaTamano(
                    etiqueta = tamano?.etiqueta ?: "Ración",
                    precio = producto.precioSegunTamano(tamano),
                    cantidad = cantidades[tamano] ?: 0,
                    onCantidadChange = { nuevaCantidad -> onCantidadChange(tamano, nuevaCantidad) }
                )
            }
        }
    }
}

@Composable
private fun TextoCabecera(
    texto: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = texto,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        textAlign = textAlign,
        modifier = modifier
    )
}

@Composable
private fun FilaTamano(
    etiqueta: String,
    precio: Double,
    cantidad: Int,
    onCantidadChange: (Int) -> Unit
) {
    // Las filas con cantidad se resaltan para ver de un vistazo lo que se va a pedir
    val seleccionada = cantidad > 0
    val colorFondo = if (seleccionada) {
        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
    } else {
        Color.Transparent
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(colorFondo)
            .padding(start = 12.dp, top = 4.dp, bottom = 4.dp)
    ) {
        Text(
            text = etiqueta,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = if (seleccionada) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = precio.formatoPrecio(),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.weight(1f)
        )
        SelectorCantidad(
            cantidad = cantidad,
            onCantidadChange = onCantidadChange,
            modifier = Modifier.width(AnchoColumnaCantidad)
        )
    }
}
