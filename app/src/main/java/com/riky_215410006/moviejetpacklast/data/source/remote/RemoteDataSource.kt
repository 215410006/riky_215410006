package com.riky_215410006.moviejetpacklast.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.riky_215410006.moviejetpacklast.data.source.response.ApiResponse
import com.riky_215410006.moviejetpacklast.data.source.response.MovieResponse
import com.riky_215410006.moviejetpacklast.data.source.response.TvResponse
import com.riky_215410006.moviejetpacklast.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.await
import java.io.IOException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val movieApiService: ApiService) {

    // Mendapatkan daftar film populer dari layanan web.
    fun getPopularMovies(): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        val resultMovieResponse = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = movieApiService.getPopularMovies().await()
                resultMovieResponse.postValue(ApiResponse.success(response.result!!))
            } catch (e: IOException) {
                e.printStackTrace()
                resultMovieResponse.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultMovieResponse
    }

    // Mendapatkan daftar acara TV populer dari layanan web.
    fun getPopularTv(): LiveData<ApiResponse<List<TvResponse>>> {
        EspressoIdlingResource.increment()
        val resultTvResponse = MutableLiveData<ApiResponse<List<TvResponse>>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = movieApiService.getPopularTv().await()
                resultTvResponse.postValue(ApiResponse.success(response.result!!))
            } catch (e: IOException) {
                e.printStackTrace()
                resultTvResponse.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultTvResponse
    }
}
