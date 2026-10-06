package com.example.diariofilmesroomapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    private val viewModel: FilmeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val filmes by viewModel.filmes.collectAsState()
            val estado by viewModel.estado.collectAsState()

            MaterialTheme {
                TelaFilmes(
                    filmes = filmes,
                    estado = estado,
                    onSincronizar = { viewModel.sincronizar() }
                )
            }
        }
    }
}

@androidx.compose.runtime.Composable
fun TelaFilmes(
    filmes: List<Filme>,
    estado: SyncState,
    onSincronizar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Diário de Filmes",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Filmes salvos no Room",
            style = MaterialTheme.typography.bodyMedium
        )

        Button(
            onClick = onSincronizar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buscar filmes da API")
        }

        when (estado) {
            SyncState.Idle -> {
                Text("Pronto para sincronizar.")
            }

            SyncState.Loading -> {
                CircularProgressIndicator()
                Text("Buscando filmes...")
            }

            is SyncState.Error -> {
                Text(
                    text = "Erro de rede: ${estado.mensagem}",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        if (filmes.isEmpty()) {
            Text("Nenhum filme salvo ainda.")
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(filmes) { filme ->
                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(14.dp),
                            verticalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Text(
                                text = filme.titulo,
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text("Diretor: ${filme.diretor}")
                            Text("Ano: ${filme.ano}")
                            TextButton(onClick = {}) {
                                Text("Filme salvo no banco local")
                            }
                        }
                    }
                }
            }
        }
    }
}
