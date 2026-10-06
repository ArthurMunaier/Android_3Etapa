package com.receitafacil.app.userinterface

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Único arquivo do app que declara cores literais.
private val CoresReceitaFacil = lightColorScheme(
    primary = Color(0xFFB5441F),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFFFE0D2),
    onPrimaryContainer = Color(0xFF4A1706),
    secondary = Color(0xFF7A5C48),
    onSecondary = Color(0xFFFFFFFF),
    background = Color(0xFFFFFBF7),
    onBackground = Color(0xFF2B1D16),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF2B1D16),
    surfaceVariant = Color(0xFFF7ECE4),
    onSurfaceVariant = Color(0xFF6B5547),
)

@Composable
fun ReceitaFacilTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = CoresReceitaFacil, content = content)
}
