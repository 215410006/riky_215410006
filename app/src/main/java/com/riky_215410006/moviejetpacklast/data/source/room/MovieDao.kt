package com.riky_215410006.moviejetpacklast.data.source.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.riky_215410006.moviejetpacklast.data.source.entity.MovieEntity
import com.riky_215410006.moviejetpacklast.data.source.entity.TvEntity

@Dao
interface MovieDao {

    // Mendapatkan daftar film dari basis data lokal.
    @Query("SELECT * FROM tb_favorite_movie")
    fun getListMovies() : DataSource.Factory<Int, MovieEntity>

    // Mendapatkan daftar acara TV dari basis data lokal.
    @Query("SELECT * FROM tb_favorite_tvshow")
    fun getListTv() : DataSource.Factory<Int, TvEntity>

    // Mendapatkan daftar film favorit dari basis data lokal.
    @Query("SELECT * FROM tb_favorite_movie WHERE isFavorite = 1")
    fun getListFavoriteMovies() : DataSource.Factory<Int, MovieEntity>

    // Mendapatkan daftar acara TV favorit dari basis data lokal.
    @Query("SELECT * FROM tb_favorite_tvshow WHERE isFavorite = 1")
    fun getListFavoriteTv() : DataSource.Factory<Int, TvEntity>

    // Mendapatkan detail film berdasarkan ID dari basis data lokal.
    @Query("SELECT * FROM tb_favorite_movie WHERE movieId = :movieId")
    fun getDetailMovieById(movieId: Int) : LiveData<MovieEntity>

    // Mendapatkan detail acara TV berdasarkan ID dari basis data lokal.
    @Query("SELECT * FROM tb_favorite_tvshow WHERE tvShowId = :tvShowId")
    fun getDetailTvById(tvShowId: Int) : LiveData<TvEntity>

    // Memasukkan daftar film ke basis data lokal.
    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = MovieEntity::class)
    fun insertMovies(movies: List<MovieEntity>)

    // Memasukkan daftar acara TV ke basis data lokal.
    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TvEntity::class)
    fun insertTv(tvShows: List<TvEntity>)

    // Memperbarui data film yang ada di basis data lokal.
    @Update(entity = MovieEntity::class)
    fun updateMovie(movie : MovieEntity)

    // Memperbarui data acara TV yang ada di basis data lokal.
    @Update(entity = TvEntity::class)
    fun updateTv(tvShows: TvEntity)
}
