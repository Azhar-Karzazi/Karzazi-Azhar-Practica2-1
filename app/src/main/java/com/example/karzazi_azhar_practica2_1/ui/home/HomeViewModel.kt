package com.example.karzazi_azhar_practica2_1.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.karzazi_azhar_practica2_1.data.Bebida
import com.example.karzazi_azhar_practica2_1.data.LineaPedido
import com.example.karzazi_azhar_practica2_1.data.Pasta
import com.example.karzazi_azhar_practica2_1.data.Pizza
import com.example.karzazi_azhar_practica2_1.data.Producto
import com.example.karzazi_azhar_practica2_1.data.ProductosMock
import com.example.karzazi_azhar_practica2_1.data.Tamano
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    // Al crearse el ViewModel (al abrir la ventana) se cargan los productos
    init {
        cargarProductos()
    }

    // Carga simulada: en vez de ir a una base de datos usamos las listas mock
    private fun cargarProductos() {
        _uiState.update {
            it.copy(
                pizzas = ProductosMock.pizzas,
                bebidas = ProductosMock.bebidas,
                pastas = ProductosMock.pastas
            )
        }
    }

    fun onCantidadMas(idProducto: Int) {
        _uiState.update { estado ->
            val nueva = estado.cantidadDe(idProducto) + 1
            estado.copy(cantidades = estado.cantidades + (idProducto to nueva))
        }
    }

    fun onCantidadMenos(idProducto: Int) {
        _uiState.update { estado ->
            val nueva = estado.cantidadDe(idProducto) - 1
            // La cantidad mínima es 1
            if (nueva < 1) {
                estado
            } else {
                estado.copy(cantidades = estado.cantidades + (idProducto to nueva))
            }
        }
    }

    fun onTamanoChange(idProducto: Int, tamano: Tamano) {
        _uiState.update { estado ->
            estado.copy(tamanos = estado.tamanos + (idProducto to tamano))
        }
    }

    fun onAnadirClick(producto: Producto) {
        val estado = _uiState.value
        val tamano = estado.tamanoDe(producto.id)

        // Creamos el producto con el tamaño elegido. La pizza y la bebida
        // necesitan tamaño; la pasta no.
        val productoElegido: Producto = when (producto) {
            is Pizza -> if (tamano == null) return else producto.copy(tamano = tamano)
            is Bebida -> if (tamano == null) return else producto.copy(tamano = tamano)
            is Pasta -> producto
        }

        val lineaPedido = LineaPedido(
            producto = productoElegido,
            cantidad = estado.cantidadDe(producto.id)
        )
        val nuevoPedido = estado.pedido.copy(
            lineasPedido = estado.pedido.lineasPedido + lineaPedido
        )

        _uiState.update { it.copy(pedido = nuevoPedido) }

        Log.i("HomeViewModel", lineaPedido.toString())
        Log.i("HomeViewModel", nuevoPedido.toString())
    }
}
