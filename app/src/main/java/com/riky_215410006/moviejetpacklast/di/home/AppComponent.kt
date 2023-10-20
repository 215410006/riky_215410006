package com.riky_215410006.moviejetpacklast.di.home

import android.app.Application
import com.riky_215410006.moviejetpacklast.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class,
        NetworkModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    // Antarmuka AppComponent adalah AndroidInjector untuk BaseApplication, yang merupakan entri titik aplikasi

    @Component.Builder
    interface Builder {

        // Antarmuka Builder digunakan untuk mengonfigurasi komponen AppComponent

        @BindsInstance
        fun application(application: Application): Builder

        // Metode ini mengikat instance aplikasi (Application) yang akan digunakan dalam komponen

        fun build(): AppComponent
    }
}
