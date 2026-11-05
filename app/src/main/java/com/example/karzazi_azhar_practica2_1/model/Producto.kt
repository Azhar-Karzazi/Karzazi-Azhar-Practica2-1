package com.example.karzazi_azhar_practica2_1.model

import kotlin.math.roundToInt

/**
 * Clase base de todos los productos de la carta: Pizza, Pasta y Bebida.
 *
 * Es "sealed": solo pueden existir esas tres subclases, así que en un "when" el
 * compilador sabe que no hay más casos y no hace falta poner "else".
 *
 * El tamaño NO se guarda en el producto. El producto es lo que aparece en la carta y
 * el tamaño lo elige el cliente al pedir, por eso va en [LineaPedido]. Gracias a eso
 * se puede pedir la misma pizza en varios tamaños (una línea por cada tamaño).
 */
sealed class Producto {
    abstract val id: Int
    abstract val nombre: String

    /** Precio del tamaño mediano, o de la ración si el producto no tiene tamaños. */
    abstract val precio: Double

    /** Tamaños en los que se vende. Si está vacío, se vende en ración única. */
    open val tamanos: List<Tamano> = emptyList()

    /** Opciones que puede elegir el cliente. "null" significa ración única. */
    val opcionesTamano: List<Tamano?>
        get() = tamanos.ifEmpty { listOf(null) }

    /** Precio según el tamaño elegido, redondeado a céntimos. */
    fun precioSegunTamano(tamano: Tamano?): Double {
        val precioCalculado = if (tamano == null) precio else precio * tamano.factorPrecio
        return (precioCalculado * 100).roundToInt() / 100.0
    }
}
