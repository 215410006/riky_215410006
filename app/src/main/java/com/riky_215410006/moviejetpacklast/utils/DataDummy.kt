package com.riky_215410006.moviejetpacklast.utils

import com.riky_215410006.moviejetpacklast.data.source.entity.MovieEntity
import com.riky_215410006.moviejetpacklast.data.source.entity.TvEntity

object DataDummy {
    // Objek DataDummy yang berisi metode-metode untuk menghasilkan data dummy

    fun generateDataMovieDummy(): List<MovieEntity> {
        val listMovie = ArrayList<MovieEntity>()

        // Menambahkan data film palsu ke dalam listMovie
        listMovie.add(
            MovieEntity(
                id = 1,
                movieId = 1,
                name = "A Star Is Born",
                // Deskripsi film
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                imgPreview = "https://image.tmdb.org/t/p/original/wqtaHWOEZ3rXDJ8c6ZZShulbo18.jpg",
                isFavorite = false
            )
        )

        // Menambahkan data film lainnya seperti yang ada dalam kode

        return listMovie
    }

    fun generateDataTvShowDummy(): List<TvEntity> {
        val listTvShow = ArrayList<TvEntity>()

        // Menambahkan data acara TV palsu ke dalam listTvShow
        listTvShow.add(
            TvEntity(
                id = 11,
                tvShowId = 11,
                name = "Arrow",
                // Deskripsi acara TV
                poster = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/gKG5Gz5Ngf8fgWpBsWtlg5L2SF.jpg",
                imgPreview = "https://image.tmdb.org/t/p/original/elbLQbocvW9vwrHRjYTSjXr5BX5.jpg",
                isFavorite = false
            )
        )

        // Menambahkan data acara TV lainnya seperti yang ada dalam kode

        return listTvShow
    }
}
