package com.receitafacil.app.model

import kotlinx.serialization.Serializable

sealed interface Rota {
    @Serializable
    data object Splash : Rota

    @Serializable
    data object Login : Rota

    @Serializable
    data object ListaReceitas : Rota

    @Serializable
    data object Favoritas : Rota

    @Serializable
    data object Perfil : Rota

    @Serializable
    data class DetalheReceita(val receitaId: Int) : Rota
}
