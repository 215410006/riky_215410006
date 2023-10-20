package com.riky_215410006.moviejetpacklast.di.home

import com.riky_215410006.moviejetpacklast.ui.DetailActivity
import com.riky_215410006.moviejetpacklast.ui.homestart.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    // Mendefinisikan metode untuk memberikan kontribusi dalam penyediaan objek Activity

    // Mendefinisikan kontribusi untuk HomeActivity bersama dengan modul HomeFragmentBuildersModule
    @ContributesAndroidInjector(modules = [HomeFragmentBuildersModule::class])
    abstract fun contributeHomeActivity(): HomeActivity

    // Mendefinisikan kontribusi untuk DetailActivity
    @ContributesAndroidInjector
    abstract fun contributeDetailActivity(): DetailActivity
}
