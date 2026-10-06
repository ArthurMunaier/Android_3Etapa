package com.example.diariofilmesroomapi

import retrofit2.http.GET

data class FilmeApi(
    val id: String,
    val title: String,
    val description: String,
    val director: String,
    val release_date: String
)

interface FilmeApiService {

    @GET("films")
    suspend fun buscarFilmes(): List<FilmeApi>
}
