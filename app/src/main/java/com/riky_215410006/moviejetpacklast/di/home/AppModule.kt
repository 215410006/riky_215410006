package com.riky_215410006.moviejetpacklast.di.home

import android.app.Application
import com.riky_215410006.moviejetpacklast.data.source.MovieRepository
import com.riky_215410006.moviejetpacklast.data.source.remote.ApiService
import com.riky_215410006.moviejetpacklast.data.source.remote.RemoteDataSource
import com.riky_215410006.moviejetpacklast.data.source.room.LocalSource
import com.riky_215410006.moviejetpacklast.data.source.room.MovieDao
import com.riky_215410006.moviejetpacklast.data.source.room.MovieDatabase
import com.riky_215410006.moviejetpacklast.viewmodelfactory.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    companion object {
        // Mendefinisikan penyediaan MovieDatabase sebagai singleton
        @Singleton
        @Provides
        fun provideMovieDatabase(application: Application): MovieDatabase =
            MovieDatabase.getInstance(application)

        // Mendefinisikan penyediaan MovieDao sebagai singleton
        @Singleton
        @Provides
        fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao =
            movieDatabase.movieDao()

        // Mendefinisikan penyediaan LocalSource sebagai singleton
        @Singleton
        @Provides
        fun provideLocalSource(movieDao: MovieDao): LocalSource =
            LocalSource(movieDao)

        // Mendefinisikan penyediaan RemoteDataSource sebagai singleton
        @Singleton
        @Provides
        fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource =
            RemoteDataSource(apiService)

        // Mendefinisikan penyediaan MovieRepository sebagai singleton
        @Singleton
        @Provides
        fun provideMovieRepository(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalSource
        ): MovieRepository = MovieRepository(remoteDataSource, localDataSource)

        // Mendefinisikan penyediaan ViewModelFactory sebagai singleton
        @Singleton
        @Provides
        fun provideViewModelFactory(catalogRepository: MovieRepository): ViewModelFactory =
            ViewModelFactory(catalogRepository)
    }
}
