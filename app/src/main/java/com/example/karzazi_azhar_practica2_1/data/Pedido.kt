package com.example.karzazi_azhar_practica2_1.data

data class Pedido(
    val lineasPedido: List<LineaPedido> = emptyList()
) {
    // Número de productos totales: la suma de las cantidades de todas las líneas
    val totalProductos: Int
        get() = lineasPedido.sumOf { it.cantidad }
}
