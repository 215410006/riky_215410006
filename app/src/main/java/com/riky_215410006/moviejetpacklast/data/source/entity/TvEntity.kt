package com.riky_215410006.moviejetpacklast.data.source.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tb_favorite_tvshow")
@Parcelize
data class TvEntity(

    // Kolom id yang merupakan primary key dan akan di-generate secara otomatis
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    // Kolom tvShowId untuk menyimpan ID acara TV
    @NonNull
    @ColumnInfo(name = "tvShowId")
    var tvShowId: Int = 0,

    // Kolom name untuk menyimpan nama acara TV
    @ColumnInfo(name = "tvShowName")
    var name: String? = null,

    // Kolom desc untuk menyimpan deskripsi acara TV
    @ColumnInfo(name = "tvShowDesc")
    var desc: String? = null,

    // Kolom poster untuk menyimpan URL gambar poster acara TV
    @ColumnInfo(name = "tvShowPoster")
    var poster: String? = null,

    // Kolom imgPreview untuk menyimpan URL gambar preview acara TV
    @ColumnInfo(name = "tvShowImgPreview")
    var imgPreview: String? = null,

    // Kolom isFavorite untuk menandai apakah acara TV ini favorit atau tidak
    @NonNull
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
): Parcelable
