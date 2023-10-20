package com.riky_215410006.moviejetpacklast.di.home

import com.riky_215410006.moviejetpacklast.ui.fragment.FavoriteFragment
import com.riky_215410006.moviejetpacklast.ui.movie.MovieFragment
import com.riky_215410006.moviejetpacklast.ui.tv.TvFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentBuildersModule {
    // Mendefinisikan metode untuk memberikan dependensi dan mengonfigurasi MovieFragment
    @ContributesAndroidInjector
    abstract fun contributeMovieFragment(): MovieFragment

    // Mendefinisikan metode untuk memberikan dependensi dan mengonfigurasi TvFragment
    @ContributesAndroidInjector
    abstract fun contributeTvFragment(): TvFragment

    // Mendefinisikan metode untuk memberikan dependensi dan mengonfigurasi FavoriteFragment
    // Menggunakan modul FavoriteFragmentBuildersModule untuk konfigurasi tambahan
    @ContributesAndroidInjector(modules = [FavoriteFragmentBuildersModule::class])
    abstract fun contributeFavoriteFragment(): FavoriteFragment
}
