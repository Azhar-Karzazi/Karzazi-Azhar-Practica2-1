package com.example.karzazi_azhar_practica2_1.model

/**
 * El pedido del cliente: las líneas que va añadiendo al carrito.
 * Es inmutable: para añadir una línea se devuelve un Pedido nuevo con copy().
 */
data class Pedido(
    val lineasPedido: List<LineaPedido> = emptyList()
) {
    /** Número de productos totales: la suma de las cantidades de todas las líneas. */
    val totalProductos: Int
        get() = lineasPedido.sumOf { it.cantidad }

    val precioTotal: Double
        get() = lineasPedido.sumOf { it.subtotal }

    /**
     * Devuelve un pedido nuevo con [nuevaLinea] añadida. Si ya había una línea del mismo
     * producto y tamaño, se suman las cantidades en vez de repetir la línea.
     * La misma pizza en otro tamaño es una línea distinta.
     */
    fun anadirLinea(nuevaLinea: LineaPedido): Pedido {
        val yaExiste = lineasPedido.any { it.esMismoArticuloQue(nuevaLinea) }

        val lineasActualizadas = if (yaExiste) {
            lineasPedido.map { linea ->
                if (linea.esMismoArticuloQue(nuevaLinea)) {
                    linea.copy(cantidad = linea.cantidad + nuevaLinea.cantidad)
                } else {
                    linea
                }
            }
        } else {
            lineasPedido + nuevaLinea
        }

        return copy(lineasPedido = lineasActualizadas)
    }
}
