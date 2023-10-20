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
import com.riky_215410006.moviejetpacklast.data.source.entity.TvEntity
import com.riky_215410006.moviejetpacklast.ui.DetailActivity
import com.riky_215410006.moviejetpacklast.ui.tv.TvAdapter
import com.riky_215410006.moviejetpacklast.ui.tv.TvCallback
import com.riky_215410006.moviejetpacklast.utils.StringConst
import com.riky_215410006.moviejetpacklast.viewmodelfactory.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.empty_state.*
import kotlinx.android.synthetic.main.fragment_favorite_tv.*
import javax.inject.Inject

class FavoriteTvFragment : DaggerFragment(), TvCallback {

    private lateinit var viewModel: FavoriteViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_tv, container, false)
        // Menggembalikan tampilan (layout) fragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        // Set up RecyclerView untuk menampilkan daftar acara TV favorit

        parentFragment?.let {
            viewModel = ViewModelProvider(it, factory)[FavoriteViewModel::class.java]
        }
        // Menginisialisasi ViewModel dengan ViewModelProvider dan ViewModelFactory dari parentFragment
        observeFavoriteTvShow()
        // Memulai pengamatan (observasi) perubahan dalam daftar acara TV favorit
    }

    private fun observeFavoriteTvShow() {
        viewModel.getListFavoriteTv().observe(viewLifecycleOwner, Observer {
            if (it != null){
                rv_favorite_tvshow.adapter?.let {adapter ->
                    when (adapter) {
                        is TvAdapter -> {
                            if (it.isNullOrEmpty()){
                                rv_favorite_tvshow.visibility = View.GONE
                                enableEmptyStateEmptyFavoriteTvShow()
                            } else {
                                rv_favorite_tvshow.visibility = View.VISIBLE
                                adapter.submitList(it)
                                adapter.notifyDataSetChanged()
                            }
                        }
                    }
                }
            }
        })
        // Mengamati perubahan dalam daftar acara TV favorit dan memperbarui RecyclerView sesuai dengan perubahan tersebut
    }

    private fun setupRecyclerView() {
        rv_favorite_tvshow.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = TvAdapter(this@FavoriteTvFragment)
        }
        // Menyiapkan RecyclerView untuk menampilkan daftar acara TV
    }

    private fun enableEmptyStateEmptyFavoriteTvShow() {
        empty.text = resources.getString(R.string.fav_list)
        desc_empty.text = resources.getString(R.string.belum_ada_list)
        favorite_tvshow_empty_state.visibility = View.VISIBLE
        // Mengaktifkan tampilan kosong jika tidak ada acara TV favorit yang tersedia
    }

    override fun onItemClicked(data: TvEntity) {
        startActivity(
            Intent(
                context,
                DetailActivity::class.java
            )
                .putExtra(DetailActivity.EXTRA_DATA, data.tvShowId)
                .putExtra(DetailActivity.EXTRA_TYPE, StringConst.TYPE_TVSHOW)
        )
        // Menangani klik pada item acara TV dan membuka DetailActivity untuk menampilkan detailnya
    }
}
