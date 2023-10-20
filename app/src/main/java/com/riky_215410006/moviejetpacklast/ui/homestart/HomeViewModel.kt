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
    // Konstruktor yang menerima MovieRepository sebagai dependensi melalui dependency injection

    fun getListPopularMovie(): LiveData<Resource<PagedList<MovieEntity>>>
            = movieRepository.getPopularMovies()
    // Fungsi ini mengembalikan LiveData yang berisi sumber daya (Resource) yang berisi daftar film populer.
    // Data ini diambil dari movieRepository.

    fun getListPopularTv(): LiveData<Resource<PagedList<TvEntity>>>
            = movieRepository.getPopularTv()
    // Fungsi ini serupa dengan yang sebelumnya, tetapi digunakan untuk mengambil daftar acara TV populer dari movieRepository.
}
