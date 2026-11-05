package com.example.karzazi_azhar_practica2_1.data

// El tamaño elegido va dentro del producto (Pizza o Bebida con su tamano)
data class LineaPedido(
    val producto: Producto,
    val cantidad: Int
)
