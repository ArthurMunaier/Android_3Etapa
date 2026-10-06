package com.example.diariofilmesroomapi

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Filme::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmeDao(): FilmeDao
}
