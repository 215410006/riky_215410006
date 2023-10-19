package com.riky_215410006.moviejetpacklast.ui.movie

import com.riky_215410006.moviejetpacklast.data.source.entity.MovieEntity

interface MovieCallback {
    fun onItemClicked(data: MovieEntity)
}