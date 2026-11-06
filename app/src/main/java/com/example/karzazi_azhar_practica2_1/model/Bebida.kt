package com.example.karzazi_azhar_practica2_1.model

data class Bebida(
    override val id: Int,
    override val nombre: String,
    override val precio: Double
) : Producto() {
    // Las bebidas se pueden pedir en todos los tamaños
    override val tamanos: List<Tamano> = Tamano.entries
}
