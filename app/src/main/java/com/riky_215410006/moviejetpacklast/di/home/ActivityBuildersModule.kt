package com.riky_215410006.moviejetpacklast.di.home

import com.riky_215410006.moviejetpacklast.ui.DetailActivity
import com.riky_215410006.moviejetpacklast.ui.homestart.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(modules = [HomeFragmentBuildersModule::class])
    abstract fun contributeHomeActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailActivity(): DetailActivity
}