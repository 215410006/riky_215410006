package com.riky_215410006.moviejetpacklast.data.source.status

data class Resource<T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        // Membuat objek Resource dengan status SUCCESS dan data yang berhasil diterima.
        fun <T> success(data: T?): Resource<T> = Resource(Status.SUCCESS, data, null)

        // Membuat objek Resource dengan status ERROR, pesan kesalahan, dan data yang mungkin ada.
        fun <T> error(msg: String?, data: T?): Resource<T> = Resource(Status.ERROR, data, msg)

        // Membuat objek Resource dengan status LOADING dan data yang mungkin ada.
        fun <T> loading(data: T?): Resource<T> = Resource(Status.LOADING, data, null)
    }
}


