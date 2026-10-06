package com.receitafacil.app.userinterface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.receitafacil.app.viewmodel.ReceitaViewModel

@Composable
fun PerfilScreen(modifier: Modifier = Modifier, viewModel: ReceitaViewModel, aoSair: () -> Unit) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("👩‍🍳", style = MaterialTheme.typography.displayMedium)
        Spacer(Modifier.height(8.dp))
        Text("Seu perfil", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text("${viewModel.receitas.size} receitas no catálogo")
        Text("${viewModel.receitas.count { it.favorita }} favoritas")
        Spacer(Modifier.height(32.dp))
        OutlinedButton(onClick = { viewModel.restaurar() }) { Text("Restaurar catálogo") }
        Spacer(Modifier.height(12.dp))
        OutlinedButton(onClick = aoSair) { Text("Sair da conta") }
    }
}
