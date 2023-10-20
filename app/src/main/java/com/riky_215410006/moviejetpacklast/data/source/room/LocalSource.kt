package com.riky_215410006.moviejetpacklast.data.source.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.riky_215410006.moviejetpacklast.data.source.entity.MovieEntity
import com.riky_215410006.moviejetpacklast.data.source.entity.TvEntity
import javax.inject.Inject

class LocalSource @Inject constructor(private val movieDao: MovieDao) {

    // Mendapatkan daftar film dari basis data lokal.
    fun getListMovies() : DataSource.Factory<Int, MovieEntity> = movieDao.getListMovies()

    // Mendapatkan daftar film favorit dari basis data lokal.
    fun getListFavoriteMovies() : DataSource.Factory<Int, MovieEntity> = movieDao.getListFavoriteMovies()

    // Mendapatkan daftar acara TV dari basis data lokal.
    fun getListTv() : DataSource.Factory<Int, TvEntity> = movieDao.getListTv()

    // Mendapatkan daftar acara TV favorit dari basis data lokal.
    fun getListFavoriteTv() : DataSource.Factory<Int, TvEntity> = movieDao.getListFavoriteTv()

    // Mendapatkan detail film berdasarkan ID dari basis data lokal.
    fun getDetailMovie(movieId: Int) : LiveData<MovieEntity> = movieDao.getDetailMovieById(movieId)

    // Mendapatkan detail acara TV berdasarkan ID dari basis data lokal.
    fun getDetailTv(tvShowId: Int) : LiveData<TvEntity> = movieDao.getDetailTvById(tvShowId)

    // Memasukkan daftar film ke basis data lokal.
    fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    // Memasukkan daftar acara TV ke basis data lokal.
    fun insertTv(tvShows: List<TvEntity>) = movieDao.insertTv(tvShows)

    // Menandai atau menghapus tanda favorit dari film.
    fun setFavoriteMovie(movie : MovieEntity) {
        movie.isFavorite = !movie.isFavorite
        movieDao.updateMovie(movie)
    }

    // Menandai atau menghapus tanda favorit dari acara TV.
    fun setFavoriteTv(tvShow : TvEntity) {
        tvShow.isFavorite = !tvShow.isFavorite
        movieDao.updateTv(tvShow)
    }
}
