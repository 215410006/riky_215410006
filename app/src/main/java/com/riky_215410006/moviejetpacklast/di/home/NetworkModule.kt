package com.riky_215410006.moviejetpacklast.di.home

import com.riky_215410006.moviejetpacklast.BuildConfig
import com.riky_215410006.moviejetpacklast.data.source.remote.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    companion object {

        // Menyediakan instance OkHttpClient yang digunakan untuk mengonfigurasi koneksi jaringan.
        @Singleton
        @Provides
        fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
        }.build()

        // Menyediakan instance Retrofit yang digunakan untuk berinteraksi dengan API jaringan.
        @Singleton
        @Provides
        fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder().apply {
            baseUrl(BuildConfig.BASE_URL_TMDB) // Mengatur base URL dari layanan API
            client(okHttpClient) // Menggunakan OkHttpClient yang telah disediakan sebagai dependensi
            addConverterFactory(GsonConverterFactory.create()) // Menggunakan GsonConverterFactory untuk mengonversi JSON
        }.build()

        // Menyediakan instance ApiService yang digunakan untuk membuat panggilan ke API jaringan.
        @Provides
        fun provideCatalogApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
    }
}
