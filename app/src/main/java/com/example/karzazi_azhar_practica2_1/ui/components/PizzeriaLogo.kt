package com.example.karzazi_azhar_practica2_1.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.karzazi_azhar_practica2_1.R

@Composable
fun PizzeriaLogo(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.icon),
        contentDescription = "Logo de la pizzería",
        modifier = modifier.size(120.dp)
    )
}
