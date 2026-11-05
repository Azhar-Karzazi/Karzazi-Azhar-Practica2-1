package com.example.karzazi_azhar_practica2_1.ui.home

import com.example.karzazi_azhar_practica2_1.data.Bebida
import com.example.karzazi_azhar_practica2_1.data.Pasta
import com.example.karzazi_azhar_practica2_1.data.Pedido
import com.example.karzazi_azhar_practica2_1.data.Pizza
import com.example.karzazi_azhar_practica2_1.data.Tamano

data class HomeUiState(
    val pizzas: List<Pizza> = emptyList(),
    val bebidas: List<Bebida> = emptyList(),
    val pastas: List<Pasta> = emptyList(),
    // Lo que el usuario va eligiendo en cada tarjeta (clave = id del producto)
    val cantidades: Map<Int, Int> = emptyMap(),
    val tamanos: Map<Int, Tamano> = emptyMap(),
    // El carrito
    val pedido: Pedido = Pedido()
) {
    // Si el usuario no ha tocado la cantidad, es 1
    fun cantidadDe(idProducto: Int): Int = cantidades[idProducto] ?: 1

    // Si no ha elegido tamaño, devuelve null
    fun tamanoDe(idProducto: Int): Tamano? = tamanos[idProducto]

    // Número que se muestra en el icono del carrito
    val totalProductos: Int
        get() = pedido.totalProductos
}
