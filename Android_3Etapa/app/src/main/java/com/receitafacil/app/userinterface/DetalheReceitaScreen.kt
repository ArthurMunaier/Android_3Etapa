package com.receitafacil.app.userinterface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.receitafacil.app.model.Receita

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalheReceitaScreen(
    receita: Receita?,
    aoVoltar: () -> Unit,
    aoAlternarFavorita: () -> Unit,
    aoRemover: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(receita?.nome ?: "Receita") },
                navigationIcon = {
                    IconButton(onClick = aoVoltar) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar para a lista")
                    }
                },
                actions = {
                    if (receita != null) {
                        IconButton(onClick = aoRemover) {
                            Icon(Icons.Filled.Delete, contentDescription = "Remover ${receita.nome} do catálogo")
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { padding ->
        if (receita == null) {
            Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("Receita não encontrada.")
            }
            return@Scaffold
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(receita.emoji, fontSize = 64.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
            Text(receita.nome, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text(
                "${receita.categoria} • ${receita.minutos} min",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(8.dp))
            Text("Ingredientes", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            receita.ingredientes.forEach { Text("• $it") }
            Spacer(Modifier.height(8.dp))
            Text("Modo de preparo", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Text(receita.modoPreparo)
            Spacer(Modifier.height(16.dp))
            Button(onClick = aoAlternarFavorita, modifier = Modifier.fillMaxWidth().height(52.dp)) {
                Text(if (receita.favorita) "Remover dos favoritos" else "Adicionar aos favoritos")
            }
        }
    }
}
