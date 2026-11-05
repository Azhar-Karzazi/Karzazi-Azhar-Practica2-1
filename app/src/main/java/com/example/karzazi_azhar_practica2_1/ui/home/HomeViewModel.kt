package com.example.karzazi_azhar_practica2_1.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.karzazi_azhar_practica2_1.data.ProductosMock
import com.example.karzazi_azhar_practica2_1.model.LineaPedido
import com.example.karzazi_azhar_practica2_1.model.Producto
import com.example.karzazi_azhar_practica2_1.model.Tamano
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    // Privado: solo el ViewModel puede cambiar el estado
    private val _uiState = MutableStateFlow(HomeUiState())

    // La UI solo puede leerlo
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    // Al crearse el ViewModel (al abrir la ventana) se cargan los productos
    init {
        cargarProductos()
    }

    // Carga simulada: en vez de ir a una base de datos usamos las listas mock
    private fun cargarProductos() {
        _uiState.update {
            it.copy(
                pizzas = ProductosMock.pizzas,
                pastas = ProductosMock.pastas,
                bebidas = ProductosMock.bebidas
            )
        }
    }

    /** El usuario cambia la cantidad de una fila (con los botones o escribiéndola). */
    fun onCantidadChange(idProducto: Int, tamano: Tamano?, nuevaCantidad: Int) {
        val cantidad = nuevaCantidad.coerceIn(0, LineaPedido.CANTIDAD_MAXIMA)
        _uiState.update {
            it.copy(cantidades = it.cantidades + (Seleccion(idProducto, tamano) to cantidad))
        }
    }

    /**
     * Crea una LineaPedido por cada tamaño con cantidad mayor que 0 y la añade al Pedido.
     * Así se pueden pedir a la vez, por ejemplo, 2 Margaritas medianas y 1 grande.
     */
    fun onAnadirClick(producto: Producto) {
        val estado = _uiState.value

        val lineasNuevas = producto.opcionesTamano
            .map { tamano -> LineaPedido(producto, tamano, estado.cantidadDe(producto.id, tamano)) }
            .filter { it.cantidad > 0 }

        if (lineasNuevas.isEmpty()) return

        var pedido = estado.pedido
        lineasNuevas.forEach { lineaPedido ->
            pedido = pedido.anadirLinea(lineaPedido)
            Log.i(TAG, "LineaPedido: $lineaPedido")
        }
        Log.i(TAG, "Pedido: $pedido")
        Log.i(TAG, "Total del pedido: ${pedido.totalProductos} productos, ${pedido.precioTotal} €")

        // Se guarda el pedido y se vacía la tabla de esa tarjeta
        _uiState.update {
            it.copy(
                pedido = pedido,
                cantidades = it.cantidades.filterKeys { seleccion -> seleccion.idProducto != producto.id }
            )
        }
    }

    private companion object {
        const val TAG = "HomeViewModel"
    }
}
