package com.example.karzazi_azhar_practica2_1.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

/**
 * Cabecera roja de la ventana "Catálogo de productos": logo, nombre de la pizzería,
 * resumen del carrito y el icono del carrito con su badge.
 */
@Composable
fun CabeceraCatalogo(
    totalProductos: Int,
    precioTotal: Double,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        shape = RoundedCornerShape(24.dp),
        shadowElevation = 6.dp,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            PizzeriaLogo(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = "La Pizzetta", style = MaterialTheme.typography.headlineSmall)
                Text(text = "Catálogo de productos", style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = if (totalProductos == 0) {
                        "Tu carrito está vacío"
                    } else {
                        "${textoProductos(totalProductos)} · ${precioTotal.formatoPrecio()}"
                    },
                    style = MaterialTheme.typography.labelLarge,
                    color = LocalContentColor.current.copy(alpha = 0.85f)
                )
            }

            CarritoConBadge(total = totalProductos)
        }
    }
}
