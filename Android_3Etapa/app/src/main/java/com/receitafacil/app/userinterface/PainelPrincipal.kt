package com.receitafacil.app.userinterface

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.receitafacil.app.model.Rota
import com.receitafacil.app.viewmodel.ReceitaViewModel

enum class AbaPrincipal { RECEITAS, FAVORITAS, PERFIL }

@Composable
fun PainelPrincipal(
    navController: NavHostController,
    viewModel: ReceitaViewModel,
    abaAtual: AbaPrincipal
) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = abaAtual == AbaPrincipal.RECEITAS,
                    onClick = { navegarParaAba(navController, Rota.ListaReceitas) },
                    icon = { Icon(Icons.Filled.Restaurant, contentDescription = "Ir para o catálogo de receitas") },
                    label = { Text("Receitas") }
                )
                NavigationBarItem(
                    selected = abaAtual == AbaPrincipal.FAVORITAS,
                    onClick = { navegarParaAba(navController, Rota.Favoritas) },
                    icon = { Icon(Icons.Filled.Favorite, contentDescription = "Ir para as receitas favoritas") },
                    label = { Text("Favoritas") }
                )
                NavigationBarItem(
                    selected = abaAtual == AbaPrincipal.PERFIL,
                    onClick = { navegarParaAba(navController, Rota.Perfil) },
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Ir para o perfil") },
                    label = { Text("Perfil") }
                )
            }
        }
    ) { paddingValues ->
        val modifier = Modifier.padding(paddingValues)
        val abrirDetalhe: (Int) -> Unit = { id -> navController.navigate(Rota.DetalheReceita(id)) }

        when (abaAtual) {
            AbaPrincipal.RECEITAS -> ListaReceitasScreen(modifier, viewModel, abrirDetalhe)
            AbaPrincipal.FAVORITAS -> FavoritasScreen(modifier, viewModel, abrirDetalhe)
            AbaPrincipal.PERFIL -> PerfilScreen(
                modifier = modifier,
                viewModel = viewModel,
                aoSair = {
                    viewModel.fazerLogout()
                    navController.navigate(Rota.Login) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}

/**
 * As três opções juntas fazem a aba "lembrar" o estado (ex.: rolagem da lista):
 * saveState + restoreState + launchSingleTop.
 * O popUpTo aponta para a 1ª aba (ListaReceitas), que sempre está na pilha
 * depois do Splash/Login — assim o estado salvo é realmente restaurado.
 */
private fun navegarParaAba(navController: NavHostController, destino: Rota) {
    navController.navigate(destino) {
        popUpTo<Rota.ListaReceitas> { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}
