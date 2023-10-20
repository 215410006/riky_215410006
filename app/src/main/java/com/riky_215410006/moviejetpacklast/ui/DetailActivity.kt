package com.riky_215410006.moviejetpacklast.ui

import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.riky_215410006.moviejetpacklast.BuildConfig
import com.riky_215410006.moviejetpacklast.R
import com.riky_215410006.moviejetpacklast.data.source.entity.MovieEntity
import com.riky_215410006.moviejetpacklast.data.source.entity.TvEntity
import com.riky_215410006.moviejetpacklast.utils.StringConst
import com.riky_215410006.moviejetpacklast.utils.StringConst.TYPE_MOVIE
import com.riky_215410006.moviejetpacklast.utils.StringConst.TYPE_TVSHOW
import com.riky_215410006.moviejetpacklast.utils.loadFromUrl
import com.riky_215410006.moviejetpacklast.viewmodelfactory.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import javax.inject.Inject

class DetailActivity : DaggerAppCompatActivity() {
    // Kelas DetailActivity yang mewarisi DaggerAppCompatActivity

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var viewModel: DetailViewModel
    // Properti untuk mengakses ViewModel

    @Inject
    lateinit var factory: ViewModelFactory
    // Properti untuk mengakses ViewModelFactory melalui Dependency Injection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        // Menyiapkan tampilan aktivitas menggunakan layout activity_detail.xml

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collapsing_bar.setExpandedTitleColor(Color.TRANSPARENT)
        // Mengatur tampilan ActionBar dan judul kolaps untuk toolbar

        viewModel = ViewModelProvider(this@DetailActivity, factory)[DetailViewModel::class.java]
        // Menginisialisasi ViewModel menggunakan ViewModelProvider dan ViewModelFactory

        val id = intent.getIntExtra(EXTRA_DATA, 0)
        val type = intent.getStringExtra(EXTRA_TYPE)
        // Mendapatkan data tambahan (extra) dari intent, yaitu ID dan tipe (film atau acara TV).

        if (type.equals(TYPE_MOVIE, ignoreCase = true)) {
            setupToolbarTitle(resources.getString(R.string.detail_movie))
            viewModel.getMovieDetail(id).observe(this, Observer {
                displayData(it, null)
            })
            // Jika tipe adalah film, atur judul toolbar dan ambil detail film dari ViewModel.

        } else if (type.equals(TYPE_TVSHOW, ignoreCase = true)) {
            setupToolbarTitle(resources.getString(R.string.detail_tv))
            viewModel.getTvDetail(id).observe(this, Observer {
                it?.let {
                    displayData(null, it)
                }
            })
            // Jika tipe adalah acara TV, atur judul toolbar dan ambil detail acara TV dari ViewModel.
        }
    }

    private fun displayData(movie: MovieEntity?, tvShow: TvEntity?) {
        // Metode ini digunakan untuk menampilkan data detail film atau acara TV ke tampilan.

        val urlImage = movie?.poster ?: tvShow?.poster
        val urlHighlight = movie?.imgPreview ?: tvShow?.imgPreview
        val statusFavorite = movie?.isFavorite ?: tvShow?.isFavorite
        // Mengambil URL gambar, URL gambar highlight, dan status favorit (favorite) dari film atau acara TV.

        tv_detail_name.text = movie?.name ?: tvShow?.name
        tv_desc.text = movie?.desc ?: tvShow?.desc
        // Menampilkan judul dan deskripsi film atau acara TV.

        statusFavorite?.let { status ->
            setFavoriteState(status)
        }
        // Mengatur ikon favorit berdasarkan status favorit (jika ada).

        img_poster.loadFromUrl(BuildConfig.BASE_URL_IMAGE + StringConst.ENDPOINT_POSTER_SIZE_W185 + urlImage)
        img_huge.loadFromUrl(BuildConfig.BASE_URL_IMAGE + StringConst.ENDPOINT_POSTER_SIZE_W780 + urlHighlight)
        // Memuat dan menampilkan gambar poster dan gambar highlight.

        bt_favorite.setOnClickListener {
            setFavorite(movie, tvShow)
        }
        // Menangani klik tombol favorit untuk menambahkan atau menghapus dari daftar favorit.
    }

    private fun setFavorite(movie: MovieEntity?, tvShow: TvEntity?) {
        // Metode ini digunakan untuk menambah atau menghapus film atau acara TV dari daftar favorit.

        if (movie != null) {
            if (movie.isFavorite){
                showActionSnackBar("${movie.name} Removed from favorite")
            } else {
                showActionSnackBar("${movie.name} Added to favorite")
            }
            viewModel.setFavoriteMovie(movie)
            // Jika objek movie tidak null, maka tambahkan atau hapus film dari daftar favorit dan tampilkan pesan yang sesuai.
        } else {
            if (tvShow != null) {
                if (tvShow.isFavorite){
                    showActionSnackBar("${tvShow.name} Removed from favorite")
                } else {
                    showActionSnackBar("${tvShow.name} Removed from favorite")
                }
                viewModel.setFavoriteTv(tvShow)
                // Jika objek tvShow tidak null, maka tambahkan atau hapus acara TV dari daftar favorit dan tampilkan pesan yang sesuai.
            }
        }
    }

    private fun showActionSnackBar(msg: String) {
        Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT).show()
        // Menampilkan Snackbar dengan pesan tindakan (action).
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
        // Mengatur tindakan tombol navigasi atas untuk menutup aktivitas.
    }

    private fun setupToolbarTitle(title: String) {
        supportActionBar?.title = title
        // Mengatur judul toolbar (ActionBar).
    }

    private fun setFavoriteState(status: Boolean){
        if (status) {
            bt_favorite.setImageResource(R.drawable.ic_favorite_true)
        } else {
            bt_favorite.setImageResource(R.drawable.ic_favorite_false)
        }
        // Mengatur ikon favorit berdasarkan status favorit.
    }
}

