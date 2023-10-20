package com.riky_215410006.moviejetpacklast.di.home

import com.riky_215410006.moviejetpacklast.ui.fragment.FavoriteMovieFragment
import com.riky_215410006.moviejetpacklast.ui.fragment.FavoriteTvFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FavoriteFragmentBuildersModule {
    // Mendefinisikan metode untuk memberikan dependensi dan mengonfigurasi FavoriteMovieFragment
    @ContributesAndroidInjector
    abstract fun contributeFavoriteMovieFragment(): FavoriteMovieFragment

    // Mendefinisikan metode untuk memberikan dependensi dan mengonfigurasi FavoriteTvFragment
    @ContributesAndroidInjector
    abstract fun contributeFavoriteTvFragment(): FavoriteTvFragment
}
