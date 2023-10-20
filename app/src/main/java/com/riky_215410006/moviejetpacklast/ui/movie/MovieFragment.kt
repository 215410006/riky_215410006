package com.riky_215410006.moviejetpacklast.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.riky_215410006.moviejetpacklast.R
import com.riky_215410006.moviejetpacklast.data.source.entity.MovieEntity
import com.riky_215410006.moviejetpacklast.data.source.status.Status
import com.riky_215410006.moviejetpacklast.ui.DetailActivity
import com.riky_215410006.moviejetpacklast.ui.homestart.HomeViewModel
import com.riky_215410006.moviejetpacklast.utils.StringConst.TYPE_MOVIE
import com.riky_215410006.moviejetpacklast.viewmodelfactory.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.progress_bar.*
import javax.inject.Inject


class MovieFragment : DaggerFragment(), MovieCallback {
    // Kelas MovieFragment yang mewarisi DaggerFragment dan mengimplementasikan MovieCallback

    private lateinit var viewModel: HomeViewModel
    // Properti untuk mengakses ViewModel

    @Inject
    lateinit var factory: ViewModelFactory
    // Properti untuk mengakses ViewModelFactory melalui Dependency Injection

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
        // Menggembalikan tampilan (layout) fragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        // Memanggil metode setupRecyclerView() untuk menyiapkan RecyclerView

        activity?.let { setupViewModel(it) }
        // Menginisialisasi ViewModel dengan metode setupViewModel()

        observeListMovies()
        // Memulai pengamatan (observasi) perubahan dalam daftar film populer
    }

    private fun setupViewModel(fragmentActivity: FragmentActivity) {
        viewModel = ViewModelProvider(fragmentActivity, factory)[HomeViewModel::class.java]
        // Menginisialisasi ViewModel menggunakan ViewModelProvider dan ViewModelFactory
    }

    private fun observeListMovies() {
        viewModel.getListPopularMovie().observe(viewLifecycleOwner, Observer { listMovie ->
            if (listMovie != null) {
                when (listMovie.status) {
                    Status.LOADING -> progress_bar.visibility = View.VISIBLE
                    // Saat data sedang dimuat, tampilkan indikator loading.

                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        // Saat data berhasil dimuat, sembunyikan indikator loading.

                        rv_movie.adapter?.let { adapter ->
                            when (adapter) {
                                is MovieAdapter -> {
                                    adapter.submitList(listMovie.data)
                                    adapter.notifyDataSetChanged()
                                    // Perbarui daftar film pada adapter dengan data baru.
                                }
                            }
                        }
                    }

                    Status.ERROR -> {
                        progress_bar.visibility = View.GONE
                        // Jika terjadi kesalahan, sembunyikan indikator loading.

                        Toast.makeText(context, "Check your internet connection", Toast.LENGTH_SHORT).show()
                        // Tampilkan pesan kesalahan kepada pengguna.
                    }
                }
            }
        })
    }

    private fun setupRecyclerView() {
        rv_movie.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MovieAdapter(this@MovieFragment)
            // Mengatur RecyclerView dengan adapter MovieAdapter dan layoutManager LinearLayoutManager.
        }
    }

    override fun onItemClicked(data: MovieEntity) {
        startActivity(
            Intent(
                context,
                DetailActivity::class.java
            )
                .putExtra(DetailActivity.EXTRA_DATA, data.movieId)
                .putExtra(DetailActivity.EXTRA_TYPE, TYPE_MOVIE)
        )
        // Menangani klik pada item daftar film dan membuka DetailActivity untuk menampilkan detail film yang dipilih.
    }
}
