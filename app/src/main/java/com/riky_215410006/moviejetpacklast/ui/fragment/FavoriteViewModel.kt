package com.riky_215410006.moviejetpacklast.ui.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.riky_215410006.moviejetpacklast.data.source.MovieRepository
import com.riky_215410006.moviejetpacklast.data.source.entity.MovieEntity
import com.riky_215410006.moviejetpacklast.data.source.entity.TvEntity
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    fun getListFavoriteMovie(): LiveData<PagedList<MovieEntity>> = movieRepository.getListPopularMovies()

    fun getListFavoriteTv(): LiveData<PagedList<TvEntity>> = movieRepository.getListPopularTv()
}