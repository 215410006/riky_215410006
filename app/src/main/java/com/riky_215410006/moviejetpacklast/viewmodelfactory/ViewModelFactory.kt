package com.riky_215410006.moviejetpacklast.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.riky_215410006.moviejetpacklast.data.source.MovieRepository
import com.riky_215410006.moviejetpacklast.ui.DetailViewModel
import com.riky_215410006.moviejetpacklast.ui.fragment.FavoriteViewModel
import com.riky_215410006.moviejetpacklast.ui.homestart.HomeViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val mMovieRepository: MovieRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(mMovieRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(mMovieRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(mMovieRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}