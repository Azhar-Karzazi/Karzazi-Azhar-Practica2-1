package com.example.karzazi_azhar_practica2_1.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Icono de carrito con un círculo rojo (badge) encima que muestra el total.
 * Es un Box (apartado 2.3 de los apuntes): el icono va al fondo y el número
 * se superpone en la esquina de arriba a la derecha.
 */
@Composable
fun CarritoConBadge(
    total: Int,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Icon(
            imageVector = Icons.Filled.ShoppingCart,
            contentDescription = "Carrito",
            modifier = Modifier
                .padding(top = 8.dp, end = 8.dp)
                .size(32.dp)
        )
        Text(
            text = "$total",
            color = Color.White,
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .background(Color.Red, shape = CircleShape)
                .padding(horizontal = 6.dp, vertical = 2.dp)
        )
    }
}
