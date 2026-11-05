package com.example.karzazi_azhar_practica2_1.data

// Clase base de todos los productos.
// "sealed" significa que solo existen las subclases de este archivo, así que el
// compilador las conoce todas y el "when" de la tarjeta no necesita un "else".
sealed class Producto {
    abstract val id: Int
    abstract val nombre: String
    abstract val precio: Double
}

// tamano = null significa que el usuario todavía no ha elegido tamaño
data class Pizza(
    override val id: Int,
    override val nombre: String,
    override val precio: Double,
    val ingredientes: List<Ingrediente>,
    val tamano: Tamano? = null
) : Producto()

data class Bebida(
    override val id: Int,
    override val nombre: String,
    override val precio: Double,
    val tamano: Tamano? = null
) : Producto()

// La pasta no tiene tamaño
data class Pasta(
    override val id: Int,
    override val nombre: String,
    override val precio: Double,
    val ingredientes: List<Ingrediente>
) : Producto()
