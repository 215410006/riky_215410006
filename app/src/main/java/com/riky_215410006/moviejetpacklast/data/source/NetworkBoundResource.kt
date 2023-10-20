package com.riky_215410006.moviejetpacklast.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.riky_215410006.moviejetpacklast.data.source.response.ApiResponse
import com.riky_215410006.moviejetpacklast.data.source.response.StatusResponse
import com.riky_215410006.moviejetpacklast.data.source.status.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class NetworkBoundResource<ResultType, RequestType> {

    // LiveData yang akan menyimpan hasil pengambilan data
    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        // Set status awal sebagai loading
        result.value = Resource.loading(null)

        // Mendapatkan data dari sumber data lokal (misalnya, basis data)
        @Suppress("LeakingThis")
        val dbSource = loadFromDB()

        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                // Jika perlu, ambil data dari sumber data jarak jauh (misalnya, server)
                fetchFromNetwork(dbSource)
            } else {
                // Jika tidak perlu, gunakan data dari sumber data lokal
                result.addSource(dbSource) { newData ->
                    result.value = Resource.success(newData)
                }
            }
        }
    }

    private fun onFetchFailed() {
        // Handle kesalahan pengambilan data dari sumber data jarak jauh
    }

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        // Ambil data dari sumber data jarak jauh
        val apiResponse = createCall()

        result.addSource(dbSource) { newData ->
            // Set status menjadi loading dengan data dari sumber data lokal
            result.value = Resource.loading(newData)
        }

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response.status) {
                StatusResponse.SUCCESS -> {
                    // Jika pengambilan data jarak jauh berhasil
                    CoroutineScope(Dispatchers.IO).launch {
                        response.body?.let { saveCallResult(it) }

                        // Set status berhasil dan gunakan data dari sumber data lokal yang telah diperbarui
                        withContext(Dispatchers.Main) {
                            result.addSource(loadFromDB()) { newData ->
                                result.value = Resource.success(newData)
                            }
                        }
                    }
                }
                StatusResponse.ERROR -> {
                    // Jika terjadi kesalahan saat pengambilan data jarak jauh
                    onFetchFailed()

                    // Set status kesalahan dengan data dari sumber data lokal
                    result.addSource(dbSource) { newData ->
                        result.value = Resource.error(response.message, newData)
                    }
                }
            }
        }
    }

    // Mendapatkan LiveData hasil dari proses pengambilan data
    fun asLiveData(): LiveData<Resource<ResultType>> = result
}
