package com.receitafacil.app.userinterface

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.receitafacil.app.model.Rota
import com.receitafacil.app.viewmodel.ReceitaViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    // ÚNICO lugar do app que chama viewModel(): todas as telas recebem por parâmetro.
    val viewModel: ReceitaViewModel = viewModel()

    NavHost(navController = navController, startDestination = Rota.Splash) {

        composable<Rota.Splash> {
            SplashScreen(
                aoTerminar = {
                    // Navegação CONDICIONAL: quem chama a Splash decide o destino.
                    val destino: Rota = if (viewModel.estaLogado) Rota.ListaReceitas else Rota.Login
                    navController.navigate(destino) {
                        popUpTo(Rota.Splash) { inclusive = true }
                    }
                }
            )
        }

        composable<Rota.Login> {
            LoginScreen(
                aoLogar = {
                    viewModel.fazerLogin()
                    navController.navigate(Rota.ListaReceitas) {
                        popUpTo(Rota.Login) { inclusive = true }
                    }
                }
            )
        }

        composable<Rota.ListaReceitas> {
            PainelPrincipal(navController, viewModel, AbaPrincipal.RECEITAS)
        }

        composable<Rota.Favoritas> {
            PainelPrincipal(navController, viewModel, AbaPrincipal.FAVORITAS)
        }

        composable<Rota.Perfil> {
            PainelPrincipal(navController, viewModel, AbaPrincipal.PERFIL)
        }

        composable<Rota.DetalheReceita> { backStackEntry ->
            val rota: Rota.DetalheReceita = backStackEntry.toRoute()
            DetalheReceitaScreen(
                receita = viewModel.buscarReceita(rota.receitaId),
                aoVoltar = { navController.popBackStack() },
                aoAlternarFavorita = { viewModel.alternarFavorita(rota.receitaId) },
                aoRemover = {
                    navController.popBackStack()
                    viewModel.remover(rota.receitaId)
                }
            )
        }
    }
}
