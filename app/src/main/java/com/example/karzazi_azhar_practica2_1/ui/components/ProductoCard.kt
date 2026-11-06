package com.example.karzazi_azhar_practica2_1.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.DinnerDining
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.LocalPizza
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.karzazi_azhar_practica2_1.data.ProductosMock
import com.example.karzazi_azhar_practica2_1.model.Bebida
import com.example.karzazi_azhar_practica2_1.model.Pasta
import com.example.karzazi_azhar_practica2_1.model.Pizza
import com.example.karzazi_azhar_practica2_1.model.Producto
import com.example.karzazi_azhar_practica2_1.model.Tamano
import com.example.karzazi_azhar_practica2_1.ui.theme.KarzaziAzharPractica21Theme

/**
 * Tarjeta de un producto. Sirve para cualquier subclase de Producto y muestra la
 * información propia de cada una (ingredientes de pizza y pasta, alcohol de la bebida).
 *
 * No guarda estado: recibe las cantidades elegidas y avisa con las lambdas
 * (state hoisting).
 */
@Composable
fun ProductoCard(
    producto: Producto,
    cantidades: Map<Tamano?, Int>,
    onCantidadChange: (Tamano?, Int) -> Unit,
    onAnadirClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val cantidadTotal = cantidades.values.sum()
    val importe = cantidades.entries.sumOf { (tamano, cantidad) ->
        producto.precioSegunTamano(tamano) * cantidad
    }

    ElevatedCard(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
        ),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            CabeceraProducto(producto = producto)

            Spacer(modifier = Modifier.height(12.dp))

            TablaTamanos(
                producto = producto,
                cantidades = cantidades,
                onCantidadChange = onCantidadChange
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Solo se puede añadir si se ha elegido al menos una unidad
            Button(
                onClick = onAnadirClick,
                enabled = cantidadTotal > 0,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.AddShoppingCart,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = when {
                        cantidadTotal > 0 ->
                            "Añadir ${textoProductos(cantidadTotal)} · ${importe.formatoPrecio()}"
                        producto.tamanos.isEmpty() -> "Elige la cantidad"
                        else -> "Elige tamaño y cantidad"
                    }
                )
            }
        }
    }
}

// Icono, nombre, información según la subclase y precio "desde"
@Composable
private fun CabeceraProducto(producto: Producto) {
    val icono: ImageVector = when (producto) {
        is Pizza -> Icons.Filled.LocalPizza
        is Pasta -> Icons.Filled.DinnerDining
        is Bebida -> Icons.Filled.LocalDrink
    }
    val colorIcono = when (producto) {
        is Pizza -> MaterialTheme.colorScheme.primaryContainer
        is Pasta -> MaterialTheme.colorScheme.secondaryContainer
        is Bebida -> MaterialTheme.colorScheme.tertiaryContainer
    }
    // Cada subclase enseña su propia información
    val descripcion = when (producto) {
        is Pizza -> producto.ingredientes.joinToString(" · ") { it.nombre }
        is Pasta -> producto.ingredientes.joinToString(" · ") { it.nombre }
        is Bebida -> "Sin alcohol"
    }
    val precioMinimo = producto.opcionesTamano.minOf { producto.precioSegunTamano(it) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Surface(
            shape = CircleShape,
            color = colorIcono,
            contentColor = contentColorFor(colorIcono)
        ) {
            Icon(
                imageVector = icono,
                contentDescription = null,
                modifier = Modifier
                    .padding(10.dp)
                    .size(28.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(text = producto.nombre, style = MaterialTheme.typography.titleMedium)
            Text(
                text = descripcion,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Column(horizontalAlignment = Alignment.End) {
            if (producto.tamanos.isNotEmpty()) {
                Text(
                    text = "desde",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Text(
                text = precioMinimo.formatoPrecio(),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductoCardPreview() {
    KarzaziAzharPractica21Theme {
        ProductoCard(
            producto = ProductosMock.pizzas.first(),
            cantidades = mapOf(Tamano.MEDIANA to 2, Tamano.GRANDE to 1),
            onCantidadChange = { _, _ -> },
            onAnadirClick = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}
