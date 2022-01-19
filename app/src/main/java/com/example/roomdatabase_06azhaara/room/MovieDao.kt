package com.example.roomdatabase_06azhaara.room

import androidx.room.*


@Dao
interface MovieDao {

    @Insert
    suspend fun addMovie(barang: Movie)

    @Update
    suspend fun updateMovie(barang: Movie)

    @Delete
    suspend fun deleteMovie(barang: Movie)

    @Query("SELECT * FROM Movie")
    suspend fun getMovie(): List<Movie>
}