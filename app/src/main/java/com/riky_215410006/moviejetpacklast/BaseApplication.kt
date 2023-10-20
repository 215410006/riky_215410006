package com.riky_215410006.moviejetpacklast

import com.riky_215410006.moviejetpacklast.di.home.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class BaseApplication : DaggerApplication() {
    // Override metode untuk menyediakan AndroidInjector
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        // Membangun komponen Dagger menggunakan DaggerAppComponent
        DaggerAppComponent.builder().application(this).build()
}
