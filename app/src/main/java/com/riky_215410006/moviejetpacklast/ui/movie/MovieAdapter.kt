package com.riky_215410006.moviejetpacklast.ui.movie


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.riky_215410006.moviejetpacklast.BuildConfig
import com.riky_215410006.moviejetpacklast.R
import com.riky_215410006.moviejetpacklast.data.source.entity.MovieEntity
import com.riky_215410006.moviejetpacklast.utils.StringConst
import com.riky_215410006.moviejetpacklast.utils.loadFromUrl
import kotlinx.android.synthetic.main.item_data.view.*

class MovieAdapter (private val callback: MovieCallback) :
    PagedListAdapter<MovieEntity, MovieAdapter.ListViewHolder>(DIFF_CALLBACK) {
    // Kelas MovieAdapter yang menerima callback MovieCallback dan mewarisi PagedListAdapter

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.movieId == newItem.movieId
            }
            // Memeriksa apakah item-item yang ada di daftar sama berdasarkan ID film (movieId).

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
            // Memeriksa apakah isi dari dua item dalam daftar sama.
        }
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Kelas inner ListViewHolder yang mewarisi RecyclerView.ViewHolder

        fun bind(data: MovieEntity) {
            with(itemView) {
                data.poster?.let {
                    img_data.loadFromUrl(BuildConfig.BASE_URL_IMAGE + StringConst.ENDPOINT_POSTER_SIZE_W185 + it)
                }
                // Memuat gambar poster film dari URL dengan menggunakan ekstensi loadFromUrl

                tv_data_title.text = data.name
                tv_data_desc.text = data.desc
                // Mengatur judul dan deskripsi film

                card_item.setOnClickListener {
                    callback.onItemClicked(data)
                }
                // Menangani klik pada item daftar film dan memanggil callback onItemClicked untuk tindakan selanjutnya.
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        )
    // Membuat dan mengembalikan ListViewHolder dengan menginflasi layout item_data.

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
        // Mengikat data film ke holder (tampilan) di posisi tertentu dalam daftar.
    }
}

