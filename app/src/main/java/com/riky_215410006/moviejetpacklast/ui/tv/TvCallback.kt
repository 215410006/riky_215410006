package com.riky_215410006.moviejetpacklast.ui.tv

import com.riky_215410006.moviejetpacklast.data.source.entity.TvEntity

interface TvCallback {
    fun onItemClicked(data: TvEntity)
}