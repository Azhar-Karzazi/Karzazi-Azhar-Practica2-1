package com.example.karzazi_azhar_practica2_1.data

// Datos simulados ("mock"): una lista por cada tipo de producto
object ProductosMock {

    private fun ingredientes(vararg nombres: String): List<Ingrediente> =
        nombres.map { Ingrediente(it) }

    val pizzas = listOf(
        Pizza(
            id = 1,
            nombre = "Margarita",
            precio = 8.5,
            ingredientes = ingredientes("Mozzarella", "Tomate", "Albahaca")
        ),
        Pizza(
            id = 2,
            nombre = "Pepperoni",
            precio = 9.0,
            ingredientes = ingredientes("Mozzarella", "Pepperoni")
        ),
        Pizza(
            id = 3,
            nombre = "Cuatro Quesos",
            precio = 10.5,
            ingredientes = ingredientes("Mozzarella", "Gorgonzola", "Parmesano", "Provolone")
        )
    )

    val bebidas = listOf(
        Bebida(id = 4, nombre = "Coca-Cola", precio = 2.0),
        Bebida(id = 5, nombre = "Agua", precio = 1.0),
        Bebida(id = 6, nombre = "Cerveza", precio = 2.5)
    )

    val pastas = listOf(
        Pasta(
            id = 7,
            nombre = "Espaguetis Boloñesa",
            precio = 8.0,
            ingredientes = ingredientes("Espaguetis", "Tomate", "Carne picada")
        ),
        Pasta(
            id = 8,
            nombre = "Carbonara",
            precio = 8.5,
            ingredientes = ingredientes("Espaguetis", "Huevo", "Bacon", "Parmesano")
        ),
        Pasta(
            id = 9,
            nombre = "Macarrones con queso",
            precio = 7.5,
            ingredientes = ingredientes("Macarrones", "Queso", "Nata")
        )
    )
}
