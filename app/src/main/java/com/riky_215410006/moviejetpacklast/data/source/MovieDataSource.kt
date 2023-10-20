package com.riky_215410006.moviejetpacklast.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.riky_215410006.moviejetpacklast.data.source.entity.MovieEntity
import com.riky_215410006.moviejetpacklast.data.source.entity.TvEntity

interface MovieDataSource {
    // Mendapatkan daftar film populer dalam bentuk LiveData yang berisi objek Resource dengan tipe data PagedList<MovieEntity>.
    fun getPopularMovies(): LiveData<com.riky_215410006.moviejetpacklast.data.source.status.Resource<PagedList<MovieEntity>>>

    // Mendapatkan daftar film populer dalam bentuk LiveData yang berisi objek PagedList<MovieEntity>.
    fun getListPopularMovies(): LiveData<PagedList<MovieEntity>>

    // Mendapatkan detail film berdasarkan ID dalam bentuk LiveData yang berisi objek MovieEntity.
    fun getMovieDetail(movieId: Int): LiveData<MovieEntity>

    // Mendapatkan daftar acara TV populer dalam bentuk LiveData yang berisi objek Resource dengan tipe data PagedList<TvEntity>.
    fun getPopularTv(): LiveData<com.riky_215410006.moviejetpacklast.data.source.status.Resource<PagedList<TvEntity>>>

    // Mendapatkan daftar acara TV populer dalam bentuk LiveData yang berisi objek PagedList<TvEntity>.
    fun getListPopularTv(): LiveData<PagedList<TvEntity>>

    // Mendapatkan detail acara TV berdasarkan ID dalam bentuk LiveData yang berisi objek TvEntity.
    fun getTvDetail(tvShowId: Int): LiveData<TvEntity>

    // Menandai atau menghapus tanda favorit dari film.
    fun setFavoriteMovie(movie: MovieEntity)

    // Menandai atau menghapus tanda favorit dari acara TV.
    fun setFavoriteTv(tvShow: TvEntity)
}
