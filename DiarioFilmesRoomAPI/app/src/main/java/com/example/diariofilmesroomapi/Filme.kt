package com.example.diariofilmesroomapi

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "filmes")
data class Filme(
    @PrimaryKey val id: String,
    val titulo: String,
    val descricao: String,
    val diretor: String,
    val ano: String
)
