package com.example.karzazi_azhar_practica2_1.data

import com.example.karzazi_azhar_practica2_1.model.Bebida
import com.example.karzazi_azhar_practica2_1.model.Ingrediente
import com.example.karzazi_azhar_practica2_1.model.Pasta
import com.example.karzazi_azhar_practica2_1.model.Pizza

/**
 * Datos simulados ("mock") de la carta: una lista por cada tipo de producto.
 * Más adelante estos datos vendrán de una base de datos o de una API.
 */
object ProductosMock {

    private fun ingredientes(vararg nombres: String): List<Ingrediente> =
        nombres.map { Ingrediente(it) }

    val pizzas = listOf(
        Pizza(
            id = 1,
            nombre = "Margarita",
            precio = 8.50,
            ingredientes = ingredientes("Tomate", "Mozzarella", "Albahaca")
        ),
        Pizza(
            id = 2,
            nombre = "Pepperoni",
            precio = 9.50,
            ingredientes = ingredientes("Tomate", "Mozzarella", "Pepperoni")
        ),
        Pizza(
            id = 3,
            nombre = "Cuatro Quesos",
            precio = 10.00,
            ingredientes = ingredientes("Mozzarella", "Gorgonzola", "Parmesano", "Queso de cabra")
        ),
        Pizza(
            id = 4,
            nombre = "Barbacoa",
            precio = 10.50,
            ingredientes = ingredientes("Salsa barbacoa", "Mozzarella", "Carne picada", "Bacon", "Cebolla")
        )
    )

    val pastas = listOf(
        Pasta(
            id = 5,
            nombre = "Espaguetis a la Carbonara",
            precio = 9.00,
            ingredientes = ingredientes("Espaguetis", "Huevo", "Guanciale", "Parmesano", "Pimienta")
        ),
        Pasta(
            id = 6,
            nombre = "Macarrones a la Boloñesa",
            precio = 8.50,
            ingredientes = ingredientes("Macarrones", "Tomate", "Carne picada", "Orégano")
        ),
        Pasta(
            id = 7,
            nombre = "Tagliatelle al Pesto",
            precio = 8.75,
            ingredientes = ingredientes("Tagliatelle", "Albahaca", "Piñones", "Parmesano", "Aceite de oliva")
        )
    )

    val bebidas = listOf(
        Bebida(id = 8, nombre = "Coca-Cola", precio = 2.00, conAlcohol = false),
        Bebida(id = 9, nombre = "Agua mineral", precio = 1.50, conAlcohol = false),
        Bebida(id = 10, nombre = "Limonada casera", precio = 2.50, conAlcohol = false),
        Bebida(id = 11, nombre = "Cerveza", precio = 2.50, conAlcohol = true)
    )
}
