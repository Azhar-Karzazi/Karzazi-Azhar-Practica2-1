package com.example.karzazi_azhar_practica2_1.ui.components

import java.util.Locale

private val localeEspana: Locale = Locale.forLanguageTag("es-ES")

/** 8.5 -> "8,50 €" */
fun Double.formatoPrecio(): String = String.format(localeEspana, "%.2f €", this)

/** 1 -> "1 producto", 3 -> "3 productos" */
fun textoProductos(cantidad: Int): String =
    if (cantidad == 1) "1 producto" else "$cantidad productos"
