package com.riky_215410006.moviejetpacklast.data.source.response

class ApiResponse<T>(val status: StatusResponse, val body: T?, val message: String?) {
    companion object {
        // Membuat objek ApiResponse dengan status SUCCESS dan data yang berhasil diterima.
        fun <T> success(body: T): ApiResponse<T> = ApiResponse(StatusResponse.SUCCESS, body, null)

        // Membuat objek ApiResponse dengan status ERROR, pesan kesalahan, dan data yang mungkin ada.
        fun <T> error(msg: String, body: T): ApiResponse<T> = ApiResponse(StatusResponse.ERROR, body, msg)
    }
}
