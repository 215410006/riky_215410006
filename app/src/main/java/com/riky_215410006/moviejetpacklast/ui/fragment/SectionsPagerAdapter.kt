package com.riky_215410006.moviejetpacklast.ui.fragment

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.riky_215410006.moviejetpacklast.R

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    // Konstruktor menerima sebuah konteks dan FragmentManager

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.tab_title_movie, R.string.tab_title_tvshow)
        // Daftar judul tab yang akan ditampilkan dalam tabbed layout
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> FavoriteMovieFragment()
            // Ketika posisi adalah 0, kembalikan instance dari FavoriteMovieFragment
            1 -> FavoriteTvFragment()
            // Ketika posisi adalah 1, kembalikan instance dari FavoriteTvFragment
            else -> Fragment()
            // Untuk posisi lain, kembalikan fragment kosong
        }

    override fun getPageTitle(position: Int): CharSequence? = mContext.resources.getString(TAB_TITLES[position])
    // Mengambil judul tab berdasarkan posisi dari daftar judul tab

    override fun getCount(): Int = 2
    // Mengembalikan jumlah tab, dalam hal ini, 2 (untuk film dan acara TV)
}
