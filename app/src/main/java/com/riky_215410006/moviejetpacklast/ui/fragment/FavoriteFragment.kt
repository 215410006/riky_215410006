package com.riky_215410006.moviejetpacklast.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.riky_215410006.moviejetpacklast.R
import com.riky_215410006.moviejetpacklast.viewmodelfactory.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_favorite.*
import javax.inject.Inject

class FavoriteFragment : DaggerFragment() {

    private lateinit var viewModel: FavoriteViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
        // Menggembalikan tampilan (layout) fragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        context?.let { setupViewPager(it) }
        // Setelah aktivitas terhubung, atur ViewPager
        viewModel = ViewModelProvider(this@FavoriteFragment, factory)[FavoriteViewModel::class.java]
        // Inisialisasi ViewModel menggunakan ViewModelProvider dan ViewModelFactory
    }

    private fun setupViewPager(context: Context) {
        val sectionsPagerAdapter = SectionsPagerAdapter(context, childFragmentManager)
        // Membuat adapter untuk ViewPager
        favorite_view_pager.adapter = sectionsPagerAdapter
        // Mengatur adapter ViewPager
        favorite_tab_layout.setupWithViewPager(favorite_view_pager)
        // Menghubungkan TabLayout dengan ViewPager
    }

}
