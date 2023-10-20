package com.riky_215410006.moviejetpacklast.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.riky_215410006.moviejetpacklast.R

fun ImageView.loadFromUrl(path: String) {
    // Langkah 1: Membersihkan operasi pemrosesan gambar sebelumnya (jika ada).
    Glide.with(this).clear(this)

    // Langkah 2: Memulai operasi pemrosesan gambar dengan menggunakan Glide.
    Glide.with(this)
        .setDefaultRequestOptions(
            RequestOptions()
                .placeholder(R.drawable.ic_launcher_foreground) // Gambar placeholder saat gambar sedang dimuat.
                .error(R.drawable.ic_launcher_foreground)       // Gambar yang akan ditampilkan jika terjadi kesalahan saat memuat.
        )
        .load(path) // Memuat gambar dari URL yang diberikan.
        .into(this)  // Menampilkan gambar hasil pemrosesan di ImageView ini.
}
