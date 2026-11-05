package com.example.karzazi_azhar_practica2_1.ui.home

import com.example.karzazi_azhar_practica2_1.model.Bebida
import com.example.karzazi_azhar_practica2_1.model.Pasta
import com.example.karzazi_azhar_practica2_1.model.Pedido
import com.example.karzazi_azhar_practica2_1.model.Pizza
import com.example.karzazi_azhar_practica2_1.model.Tamano

/** Una fila de la tabla de una tarjeta: un producto en un tamaño (null = ración única). */
data class Seleccion(
    val idProducto: Int,
    val tamano: Tamano?
)

data class HomeUiState(
    val pizzas: List<Pizza> = emptyList(),
    val pastas: List<Pasta> = emptyList(),
    val bebidas: List<Bebida> = emptyList(),
    // Lo que el usuario va eligiendo en las tablas de las tarjetas (todavía no está en el carrito)
    val cantidades: Map<Seleccion, Int> = emptyMap(),
    // El carrito
    val pedido: Pedido = Pedido()
) {
    /** Cantidad elegida en una fila (0 si el usuario no la ha tocado). */
    fun cantidadDe(idProducto: Int, tamano: Tamano?): Int =
        cantidades[Seleccion(idProducto, tamano)] ?: 0

    /** Cantidades elegidas en la tabla de un producto, por tamaño. */
    fun cantidadesDe(idProducto: Int): Map<Tamano?, Int> =
        cantidades
            .filterKeys { it.idProducto == idProducto }
            .mapKeys { it.key.tamano }

    /** Unidades elegidas de un producto sumando todos sus tamaños. */
    fun cantidadTotalDe(idProducto: Int): Int =
        cantidadesDe(idProducto).values.sum()
}
