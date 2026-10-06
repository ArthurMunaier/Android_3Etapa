package com.example.diariofilmesroomapi

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.flow.Flow

class FilmeRepository(context: Context) {

    private val banco = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "filmes.db"
    ).build()

    private val dao = banco.filmeDao()

    fun observarFilmes(): Flow<List<Filme>> {
        return dao.observarFilmes()
    }

    suspend fun sincronizar() {
        val resposta = RetrofitClient.api.buscarFilmes()

        val filmes = resposta.map { filme ->
            Filme(
                id = filme.id,
                titulo = filme.title,
                descricao = filme.description,
                diretor = filme.director,
                ano = filme.release_date
            )
        }

        dao.inserirFilmes(filmes)
    }
}
