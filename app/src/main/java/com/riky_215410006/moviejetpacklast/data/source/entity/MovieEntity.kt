package com.riky_215410006.moviejetpacklast.data.source.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

// Kita mendefinisikan sebuah entitas (Entity) untuk tabel database yang akan menyimpan informasi film favorit.
@Entity(tableName = "tb_favorite_movie")
@Parcelize // Menggunakan anotasi Parcelize untuk mengaktifkan Parcelable

data class MovieEntity(

    // Kolom id yang merupakan primary key dan akan di-generate secara otomatis
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    // Kolom movieId untuk menyimpan ID film
    @NonNull
    @ColumnInfo(name = "movieId")
    var movieId: Int = 0,

    // Kolom name untuk menyimpan nama film
    @ColumnInfo(name = "movieName")
    var name: String? = null,

    // Kolom desc untuk menyimpan deskripsi film
    @ColumnInfo(name = "movieDesc")
    var desc: String? = null,

    // Kolom poster untuk menyimpan URL gambar poster film
    @ColumnInfo(name = "moviePoster")
    var poster: String? = null,

    // Kolom imgPreview untuk menyimpan URL gambar preview film
    @ColumnInfo(name = "movieImgPreview")
    var imgPreview: String? = null,

    // Kolom isFavorite untuk menandai apakah film ini favorit atau tidak
    @NonNull
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
): Parcelable // Implementasi Parcelable untuk memungkinkan objek MovieEntity dikirimkan antar komponen Android
