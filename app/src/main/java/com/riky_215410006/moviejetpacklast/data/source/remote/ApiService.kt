package com.riky_215410006.moviejetpacklast.data.source.remote

import com.riky_215410006.moviejetpacklast.BuildConfig
import com.riky_215410006.moviejetpacklast.data.source.response.ListResponse
import com.riky_215410006.moviejetpacklast.data.source.response.MovieResponse
import com.riky_215410006.moviejetpacklast.data.source.response.TvResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // Menggunakan Retrofit, kita mendefinisikan endpoint untuk mengambil daftar film yang sedang diputar.
    @GET("movie/now_playing")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = BuildConfig.MOVDB_API_KEY
    ) : Call<ListResponse<MovieResponse>>

    // Endpoint untuk mengambil daftar acara TV yang sedang tayang.
    @GET("tv/on_the_air")
    fun getPopularTv(
        @Query("api_key") apiKey: String = BuildConfig.MOVDB_API_KEY
    ) : Call<ListResponse<TvResponse>>
}
