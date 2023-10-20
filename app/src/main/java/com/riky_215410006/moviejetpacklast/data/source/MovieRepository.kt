package com.riky_215410006.moviejetpacklast.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.riky_215410006.moviejetpacklast.data.source.entity.MovieEntity
import com.riky_215410006.moviejetpacklast.data.source.entity.TvEntity
import com.riky_215410006.moviejetpacklast.data.source.remote.RemoteDataSource
import com.riky_215410006.moviejetpacklast.data.source.response.ApiResponse
import com.riky_215410006.moviejetpacklast.data.source.response.MovieResponse
import com.riky_215410006.moviejetpacklast.data.source.response.TvResponse
import com.riky_215410006.moviejetpacklast.data.source.room.LocalSource
import com.riky_215410006.moviejetpacklast.data.source.status.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieRepository @Inject constructor(private val remoteDataSource: RemoteDataSource, private val localSource: LocalSource): MovieDataSource {

    // Singleton pattern untuk memastikan hanya ada satu instans MovieRepository
    companion object {
        private var INSTANCE: MovieRepository? = null

        // Metode getInstance digunakan untuk mendapatkan atau membuat instans MovieRepository
        fun getInstance(remoteDataSource: RemoteDataSource, localSource: LocalSource): MovieRepository {
            if (INSTANCE == null) {
                INSTANCE = MovieRepository(remoteDataSource, localSource)
            }
            return INSTANCE as MovieRepository
        }
    }

    // Mendapatkan daftar film populer dari berbagai sumber
    override fun getPopularMovies(): LiveData<Resource<PagedList<MovieEntity>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>() {

            // Memuat data dari sumber data lokal
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localSource.getListMovies(), config).build()
            }

            // Memeriksa apakah data harus diambil dari sumber data jarak jauh
            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            // Membuat panggilan ke sumber data jarak jauh
            public override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getPopularMovies()

            // Menyimpan hasil panggilan ke sumber data lokal
            public override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (item in data) {
                    val movie = MovieEntity(
                        null,
                        item.id,
                        item.name,
                        item.desc,
                        item.poster,
                        item.imgPreview,
                        false
                    )
                    movieList.add(movie)
                }
                localSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    // Mendapatkan daftar film favorit dari sumber data lokal
    override fun getListPopularMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localSource.getListFavoriteMovies(), config).build()
    }

    // Mendapatkan detail film berdasarkan ID dari sumber data lokal
    override fun getMovieDetail(movieId: Int): LiveData<MovieEntity> = localSource.getDetailMovie(movieId)

    // Mendapatkan daftar acara TV populer dari berbagai sumber
    override fun getPopularTv(): LiveData<Resource<PagedList<TvEntity>> {
        return object : NetworkBoundResource<PagedList<TvEntity>, List<TvResponse>>() {

            // Memuat data dari sumber data lokal
            public override fun loadFromDB(): LiveData<PagedList<TvEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localSource.getListTv(), config).build()
            }

            // Memeriksa apakah data harus diambil dari sumber data jarak jauh
            override fun shouldFetch(data: PagedList<TvEntity>?): Boolean =
                data == null || data.isEmpty()

            // Membuat panggilan ke sumber data jarak jauh
            public override fun createCall(): LiveData<ApiResponse<List<TvResponse>>> =
                remoteDataSource.getPopularTv()

            // Menyimpan hasil panggilan ke sumber data lokal
            public override fun saveCallResult(data: List<TvResponse>) {
                val tvShowList = ArrayList<TvEntity>()
                for (item in data) {
                    val tvShow = TvEntity(
                        null,
                        item.id,
                        item.name,
                        item.desc,
                        item.poster,
                        item.imgPreview,
                        false
                    )
                    tvShowList.add(tvShow)
                }
                localSource.insertTv(tvShowList)
            }

        }.asLiveData()
    }

    // Mendapatkan daftar acara TV favorit dari sumber data lokal
    override fun getListPopularTv(): LiveData<PagedList<TvEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localSource.getListFavoriteTv(), config).build()
    }

    // Mendapatkan detail acara TV berdasarkan ID dari sumber data lokal
    override fun getTvDetail(tvShowId: Int): LiveData<TvEntity> = localSource.getDetailTv(tvShowId)

    // Menandai atau menghapus tanda favorit dari film menggunakan coroutine
    override fun setFavoriteMovie(movie: MovieEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            localSource.setFavoriteMovie(movie)
        }
    }

    // Menandai atau menghapus tanda favorit dari acara TV menggunakan coroutine
    override fun setFavoriteTv(tvShow: TvEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            localSource.setFavoriteTv(tvShow)
        }
    }
}
