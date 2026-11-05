package com.example.karzazi_azhar_practica2_1.ui.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DinnerDining
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.LocalPizza
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.karzazi_azhar_practica2_1.data.ProductosMock
import com.example.karzazi_azhar_practica2_1.model.LineaPedido
import com.example.karzazi_azhar_practica2_1.model.Pedido
import com.example.karzazi_azhar_practica2_1.model.Producto
import com.example.karzazi_azhar_practica2_1.model.Tamano
import com.example.karzazi_azhar_practica2_1.ui.components.CabeceraCatalogo
import com.example.karzazi_azhar_practica2_1.ui.components.ProductoCard
import com.example.karzazi_azhar_practica2_1.ui.components.TituloSeccion
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
        onCantidadChange = viewModel::onCantidadChange,
        onAnadirClick = { producto ->
            val productosAnadidos = uiState.cantidadTotalDe(producto.id)
            viewModel.onAnadirClick(producto)

            val mensaje = if (productosAnadidos == 1) {
                "Se ha añadido 1 producto al carrito"
            } else {
                "Se han añadido $productosAnadidos productos al carrito"
            }
            Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
        },
        modifier = modifier
    )
}

// Pantalla "sin estado": solo pinta lo que recibe (state hoisting)
@Composable
fun HomeContent(
    uiState: HomeUiState,
    onCantidadChange: (idProducto: Int, tamano: Tamano?, cantidad: Int) -> Unit,
    onAnadirClick: (Producto) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {

        CabeceraCatalogo(
            totalProductos = uiState.pedido.totalProductos,
            precioTotal = uiState.pedido.precioTotal,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)
        )

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            seccionProductos(
                titulo = "Pizzas",
                icono = Icons.Filled.LocalPizza,
                productos = uiState.pizzas,
                uiState = uiState,
                onCantidadChange = onCantidadChange,
                onAnadirClick = onAnadirClick
            )
            seccionProductos(
                titulo = "Pastas",
                icono = Icons.Filled.DinnerDining,
                productos = uiState.pastas,
                uiState = uiState,
                onCantidadChange = onCantidadChange,
                onAnadirClick = onAnadirClick
            )
            seccionProductos(
                titulo = "Bebidas",
                icono = Icons.Filled.LocalDrink,
                productos = uiState.bebidas,
                uiState = uiState,
                onCantidadChange = onCantidadChange,
                onAnadirClick = onAnadirClick
            )
        }
    }
}

// Añade a la LazyColumn el título de una sección y una tarjeta por cada producto.
// Se reutiliza para pizzas, pastas y bebidas.
private fun LazyListScope.seccionProductos(
    titulo: String,
    icono: ImageVector,
    productos: List<Producto>,
    uiState: HomeUiState,
    onCantidadChange: (Int, Tamano?, Int) -> Unit,
    onAnadirClick: (Producto) -> Unit
) {
    item(key = titulo) {
        TituloSeccion(titulo = titulo, icono = icono, numeroProductos = productos.size)
    }
    items(items = productos, key = { it.id }) { producto ->
        ProductoCard(
            producto = producto,
            cantidades = uiState.cantidadesDe(producto.id),
            onCantidadChange = { tamano, cantidad -> onCantidadChange(producto.id, tamano, cantidad) },
            onAnadirClick = { onAnadirClick(producto) }
        )
    }
}

@Preview(showBackground = true, heightDp = 900)
@Composable
fun HomeContentPreview() {
    val margarita = ProductosMock.pizzas.first()
    KarzaziAzharPractica21Theme {
        HomeContent(
            uiState = HomeUiState(
                pizzas = ProductosMock.pizzas,
                pastas = ProductosMock.pastas,
                bebidas = ProductosMock.bebidas,
                cantidades = mapOf(
                    Seleccion(margarita.id, Tamano.MEDIANA) to 2,
                    Seleccion(margarita.id, Tamano.GRANDE) to 1
                ),
                pedido = Pedido().anadirLinea(
                    LineaPedido(ProductosMock.bebidas.first(), Tamano.GRANDE, 3)
                )
            ),
            onCantidadChange = { _, _, _ -> },
            onAnadirClick = {}
        )
    }
}
