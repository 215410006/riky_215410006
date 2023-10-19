package com.riky_215410006.moviejetpacklast.di.home

import com.riky_215410006.moviejetpacklast.ui.fragment.FavoriteFragment
import com.riky_215410006.moviejetpacklast.ui.movie.MovieFragment
import com.riky_215410006.moviejetpacklast.ui.tv.TvFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeMovieFragment(): MovieFragment

    @ContributesAndroidInjector
    abstract fun contributeTvFragment(): TvFragment

    @ContributesAndroidInjector(modules = [FavoriteFragmentBuildersModule::class])
    abstract fun contributeFavoriteFragment(): FavoriteFragment

}