package com.example.karzazi_azhar_practica2_1.model

/**
 * Tamaños de pizza y bebida. [factorPrecio] multiplica el precio base del producto:
 * la pequeña cuesta un 20 % menos y la grande un 30 % más que la mediana.
 */
enum class Tamano(val etiqueta: String, val factorPrecio: Double) {
    PEQUENA("Pequeña", 0.8),
    MEDIANA("Mediana", 1.0),
    GRANDE("Grande", 1.3)
}
