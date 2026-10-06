package com.receitafacil.app.userinterface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.receitafacil.app.viewmodel.ReceitaViewModel

@Composable
fun ListaReceitasScreen(
    modifier: Modifier = Modifier,
    viewModel: ReceitaViewModel,
    aoAbrirDetalhe: (Int) -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(
            "Catálogo de receitas",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(20.dp)
        )

        if (viewModel.receitas.isEmpty()) {
            EstadoVazio(
                emoji = "🍽️",
                titulo = "Nenhuma receita por aqui",
                mensagem = "Você removeu todas as receitas do catálogo.",
                textoAcao = "Restaurar receitas",
                aoAcao = { viewModel.restaurar() }
            )
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(viewModel.receitas, key = { it.id }) { receita ->
                    CardReceita(
                        receita = receita,
                        aoClicar = { aoAbrirDetalhe(receita.id) },
                        aoFavoritar = { viewModel.alternarFavorita(receita.id) }
                    )
                }
            }
        }
    }
}
