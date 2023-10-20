package com.riky_215410006.moviejetpacklast.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.riky_215410006.moviejetpacklast.R
import com.riky_215410006.moviejetpacklast.data.source.entity.MovieEntity
import com.riky_215410006.moviejetpacklast.ui.DetailActivity
import com.riky_215410006.moviejetpacklast.ui.movie.MovieAdapter
import com.riky_215410006.moviejetpacklast.ui.movie.MovieCallback
import com.riky_215410006.moviejetpacklast.utils.StringConst
import com.riky_215410006.moviejetpacklast.viewmodelfactory.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.empty_state.*
import kotlinx.android.synthetic.main.fragment_favorite_movie.*
import javax.inject.Inject

class FavoriteMovieFragment : DaggerFragment(), MovieCallback {

    private lateinit var viewModel: FavoriteViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
        // Menggembalikan tampilan (layout) fragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        // Set up RecyclerView untuk menampilkan daftar film favorit

        parentFragment?.let {
            viewModel = ViewModelProvider(it, factory)[FavoriteViewModel::class.java]
        }
        // Menginisialisasi ViewModel dengan ViewModelProvider dan ViewModelFactory dari parentFragment
        observeFavoriteMovies()
        // Memulai pengamatan (observasi) perubahan dalam daftar film favorit
    }

    private fun observeFavoriteMovies() {
        viewModel.getListFavoriteMovie().observe(viewLifecycleOwner, Observer {
            if (it != null){
                rv_favorite_movie.adapter?.let {adapter ->
                    when (adapter) {
                        is MovieAdapter -> {
                            if (it.isNullOrEmpty()){
                                rv_favorite_movie.visibility = View.GONE
                                enableEmptyStateEmptyFavoriteMovie()
                            } else {
                                rv_favorite_movie.visibility = View.VISIBLE
                                adapter.submitList(it)
                                adapter.notifyDataSetChanged()
                            }
                        }
                    }
                }
            }
        })
        // Mengamati perubahan dalam daftar film favorit dan memperbarui RecyclerView sesuai dengan perubahan tersebut
    }

    private fun setupRecyclerView() {
        rv_favorite_movie.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MovieAdapter(this@FavoriteMovieFragment)
        }
        // Menyiapkan RecyclerView untuk menampilkan daftar film
    }

    private fun enableEmptyStateEmptyFavoriteMovie() {
        empty.text = resources.getString(R.string.fav_list)
        desc_empty.text =
            resources.getString(R.string.belum_ada_list)
        favorite_movie_empty_state.visibility = View.VISIBLE
        // Mengaktifkan tampilan kosong jika tidak ada film favorit yang tersedia
    }

    override fun onItemClicked(data: MovieEntity) {
        startActivity(
            Intent(
                context,
                DetailActivity::class.java
            )
                .putExtra(DetailActivity.EXTRA_DATA, data.movieId)
                .putExtra(DetailActivity.EXTRA_TYPE, StringConst.TYPE_MOVIE)
        )
        // Menangani klik pada item film dan membuka DetailActivity untuk menampilkan detailnya
    }
}
