package com.example.karzazi_azhar_practica2_1.model

// La pasta se sirve en ración única, por eso no sobrescribe "tamanos"
data class Pasta(
    override val id: Int,
    override val nombre: String,
    override val precio: Double,
    val ingredientes: List<Ingrediente>
) : Producto()
