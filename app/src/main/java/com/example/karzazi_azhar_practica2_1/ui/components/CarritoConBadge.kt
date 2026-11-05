package com.example.karzazi_azhar_practica2_1.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * Icono de carrito con un círculo (badge) encima que muestra el total de productos.
 * Es un Box (apartado 2.3 de los apuntes): el icono va al fondo y el número se
 * superpone en la esquina de arriba a la derecha. El número solo aparece si hay algo.
 */
@Composable
fun CarritoConBadge(
    total: Int,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Surface(
            shape = CircleShape,
            color = LocalContentColor.current.copy(alpha = 0.15f),
            modifier = Modifier.padding(top = 6.dp, end = 6.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = "Carrito: ${textoProductos(total)}",
                modifier = Modifier
                    .padding(10.dp)
                    .size(26.dp)
            )
        }

        if (total > 0) {
            Text(
                text = if (total > 99) "99+" else "$total",
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                    .background(MaterialTheme.colorScheme.secondary, CircleShape)
                    .defaultMinSize(minWidth = 22.dp)
                    .padding(horizontal = 6.dp, vertical = 3.dp)
            )
        }
    }
}
