package com.riky_215410006.moviejetpacklast.ui.homestart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.riky_215410006.moviejetpacklast.data.source.MovieRepository
import com.riky_215410006.moviejetpacklast.data.source.entity.MovieEntity
import com.riky_215410006.moviejetpacklast.data.source.entity.TvEntity
import com.riky_215410006.moviejetpacklast.data.source.status.Resource
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    fun getListPopularMovie(): LiveData<Resource<PagedList<MovieEntity>>> = movieRepository.getPopularMovies()

    fun getListPopularTv(): LiveData<Resource<PagedList<TvEntity>>> = movieRepository.getPopularTv()

}