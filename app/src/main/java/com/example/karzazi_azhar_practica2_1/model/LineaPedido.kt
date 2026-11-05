package com.example.karzazi_azhar_practica2_1.model

/**
 * Una línea del pedido: qué producto, en qué tamaño y cuántas unidades.
 * [tamano] es null en los productos de ración única (la pasta).
 */
data class LineaPedido(
    val producto: Producto,
    val tamano: Tamano?,
    val cantidad: Int
) {
    val precioUnitario: Double
        get() = producto.precioSegunTamano(tamano)

    val subtotal: Double
        get() = precioUnitario * cantidad

    /** Dos líneas son del mismo artículo si tienen el mismo producto y el mismo tamaño. */
    fun esMismoArticuloQue(otra: LineaPedido): Boolean =
        producto.id == otra.producto.id && tamano == otra.tamano

    companion object {
        /** Máximo de unidades que se pueden elegir de golpe de un producto y tamaño. */
        const val CANTIDAD_MAXIMA = 99
    }
}
