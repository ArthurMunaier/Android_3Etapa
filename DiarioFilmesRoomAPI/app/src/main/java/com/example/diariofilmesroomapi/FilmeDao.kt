package com.example.diariofilmesroomapi

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmeDao {

    @Query("SELECT * FROM filmes ORDER BY titulo")
    fun observarFilmes(): Flow<List<Filme>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirFilmes(filmes: List<Filme>)
}
