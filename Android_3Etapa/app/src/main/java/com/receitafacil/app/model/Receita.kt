package com.receitafacil.app.model

data class Receita(
    val id: Int,
    val nome: String,
    val emoji: String,
    val categoria: String,
    val minutos: Int,
    val ingredientes: List<String>,
    val modoPreparo: String,
    val favorita: Boolean = false
)
