package com.riky_215410006.moviejetpacklast.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.riky_215410006.moviejetpacklast.data.source.MovieRepository
import com.riky_215410006.moviejetpacklast.data.source.entity.MovieEntity
import com.riky_215410006.moviejetpacklast.data.source.entity.TvEntity
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    fun getMovieDetail(movieId: Int): LiveData<MovieEntity> =
        movieRepository.getMovieDetail(movieId)

    fun getTvDetail(tvShowId: Int): LiveData<TvEntity> =
        movieRepository.getTvDetail(tvShowId)

    fun setFavoriteMovie(movie: MovieEntity){
        movieRepository.setFavoriteMovie(movie)
    }

    fun setFavoriteTv(tvShow: TvEntity){
        movieRepository.setFavoriteTv(tvShow)
    }
}