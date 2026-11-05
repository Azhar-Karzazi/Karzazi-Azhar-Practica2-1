package com.example.karzazi_azhar_practica2_1.ui.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.karzazi_azhar_practica2_1.data.Producto
import com.example.karzazi_azhar_practica2_1.data.ProductosMock
import com.example.karzazi_azhar_practica2_1.data.Tamano
import com.example.karzazi_azhar_practica2_1.ui.components.CarritoConBadge
import com.example.karzazi_azhar_practica2_1.ui.components.ProductoCard
import com.example.karzazi_azhar_practica2_1.ui.theme.KarzaziAzharPractica21Theme

// Pantalla "con estado": conecta el ViewModel con la UI
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    HomeContent(
        uiState = uiState,
        onCantidadMas = viewModel::onCantidadMas,
        onCantidadMenos = viewModel::onCantidadMenos,
        onTamanoChange = viewModel::onTamanoChange,
        onAnadirClick = { producto ->
            val cantidad = uiState.cantidadDe(producto.id)
            viewModel.onAnadirClick(producto)
            Toast.makeText(
                context,
                "Se han añadido $cantidad productos al carrito",
                Toast.LENGTH_SHORT
            ).show()
        },
        modifier = modifier
    )
}

// Pantalla "sin estado": solo pinta lo que recibe (state hoisting)
@Composable
fun HomeContent(
    uiState: HomeUiState,
    onCantidadMas: (Int) -> Unit,
    onCantidadMenos: (Int) -> Unit,
    onTamanoChange: (Int, Tamano) -> Unit,
    onAnadirClick: (Producto) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {

        // Barra superior: título a la izquierda y carrito a la derecha
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Pizzería", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            CarritoConBadge(total = uiState.totalProductos)
        }

        // Cada sección es un item de la LazyColumn
        LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
            item {
                SeccionProductos(
                    titulo = "Pizzas",
                    productos = uiState.pizzas,
                    uiState = uiState,
                    onCantidadMas = onCantidadMas,
                    onCantidadMenos = onCantidadMenos,
                    onTamanoChange = onTamanoChange,
                    onAnadirClick = onAnadirClick
                )
            }
            item {
                SeccionProductos(
                    titulo = "Pastas",
                    productos = uiState.pastas,
                    uiState = uiState,
                    onCantidadMas = onCantidadMas,
                    onCantidadMenos = onCantidadMenos,
                    onTamanoChange = onTamanoChange,
                    onAnadirClick = onAnadirClick
                )
            }
            item {
                SeccionProductos(
                    titulo = "Bebidas",
                    productos = uiState.bebidas,
                    uiState = uiState,
                    onCantidadMas = onCantidadMas,
                    onCantidadMenos = onCantidadMenos,
                    onTamanoChange = onTamanoChange,
                    onAnadirClick = onAnadirClick
                )
            }
        }
    }
}

// Un título y las tarjetas de una lista de productos
@Composable
private fun SeccionProductos(
    titulo: String,
    productos: List<Producto>,
    uiState: HomeUiState,
    onCantidadMas: (Int) -> Unit,
    onCantidadMenos: (Int) -> Unit,
    onTamanoChange: (Int, Tamano) -> Unit,
    onAnadirClick: (Producto) -> Unit
) {
    Column {
        Text(
            text = titulo,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        productos.forEach { producto ->
            ProductoCard(
                producto = producto,
                cantidad = uiState.cantidadDe(producto.id),
                tamanoSeleccionado = uiState.tamanoDe(producto.id),
                onCantidadMas = { onCantidadMas(producto.id) },
                onCantidadMenos = { onCantidadMenos(producto.id) },
                onTamanoChange = { tamano -> onTamanoChange(producto.id, tamano) },
                onAnadirClick = { onAnadirClick(producto) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    KarzaziAzharPractica21Theme {
        HomeContent(
            uiState = HomeUiState(
                pizzas = ProductosMock.pizzas,
                bebidas = ProductosMock.bebidas,
                pastas = ProductosMock.pastas
            ),
            onCantidadMas = {},
            onCantidadMenos = {},
            onTamanoChange = { _, _ -> },
            onAnadirClick = {}
        )
    }
}
