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
fun FavoritasScreen(
    modifier: Modifier = Modifier,
    viewModel: ReceitaViewModel,
    aoAbrirDetalhe: (Int) -> Unit
) {
    val favoritas = viewModel.receitas.filter { it.favorita }

    Column(modifier = modifier.fillMaxSize()) {
        Text(
            "Suas favoritas",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(20.dp)
        )

        if (favoritas.isEmpty()) {
            EstadoVazio(
                emoji = "🤍",
                titulo = "Nenhuma favorita ainda",
                mensagem = "Toque no coração de uma receita para guardá-la aqui."
            )
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(favoritas, key = { it.id }) { receita ->
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
