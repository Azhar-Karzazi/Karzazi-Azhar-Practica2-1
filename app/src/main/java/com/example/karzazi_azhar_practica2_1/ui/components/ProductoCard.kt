package com.example.karzazi_azhar_practica2_1.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.karzazi_azhar_practica2_1.data.Bebida
import com.example.karzazi_azhar_practica2_1.data.Pasta
import com.example.karzazi_azhar_practica2_1.data.Pizza
import com.example.karzazi_azhar_practica2_1.data.Producto
import com.example.karzazi_azhar_practica2_1.data.Tamano

/**
 * Tarjeta de un producto. Sirve para cualquier subclase de Producto.
 * No guarda estado: recibe la cantidad y el tamaño y avisa con las lambdas
 * (state hoisting).
 */
@Composable
fun ProductoCard(
    producto: Producto,
    cantidad: Int,
    tamanoSeleccionado: Tamano?,
    onCantidadMas: () -> Unit,
    onCantidadMenos: () -> Unit,
    onTamanoChange: (Tamano) -> Unit,
    onAnadirClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Cada subclase muestra su propia información: la pizza y la pasta enseñan
    // sus ingredientes; la bebida no tiene nada más que mostrar
    val infoExtra: String? = when (producto) {
        is Pizza -> producto.ingredientes.joinToString(", ") { it.nombre }
        is Pasta -> producto.ingredientes.joinToString(", ") { it.nombre }
        is Bebida -> null
    }

    // Solo la pizza y la bebida se pueden elegir en varios tamaños
    val admiteTamano = when (producto) {
        is Pizza, is Bebida -> true
        is Pasta -> false
    }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = producto.nombre, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            if (infoExtra != null) {
                Text(text = infoExtra)
            }
            Text(text = "${producto.precio}€", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(8.dp))

            SelectorCantidad(
                cantidad = cantidad,
                onMas = onCantidadMas,
                onMenos = onCantidadMenos
            )

            if (admiteTamano) {
                Text(
                    text = if (tamanoSeleccionado == null) {
                        "Selecciona un tamaño"
                    } else {
                        "Tamaño: ${tamanoSeleccionado.etiqueta}"
                    }
                )
                SelectorTamano(
                    tamanoSeleccionado = tamanoSeleccionado,
                    onTamanoChange = onTamanoChange
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Si el producto tiene tamaño, hay que haberlo elegido para poder añadirlo
            Button(
                onClick = onAnadirClick,
                enabled = !admiteTamano || tamanoSeleccionado != null,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Añadir al carrito")
            }
        }
    }
}
