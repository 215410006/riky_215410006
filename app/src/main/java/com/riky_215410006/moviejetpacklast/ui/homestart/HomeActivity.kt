package com.riky_215410006.moviejetpacklast.ui.homestart

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.riky_215410006.moviejetpacklast.R
import com.riky_215410006.moviejetpacklast.viewmodelfactory.ViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity() {
    // Kelas HomeActivity yang merupakan turunan dari DaggerAppCompatActivity

    private lateinit var viewModel: HomeViewModel
    // Properti untuk mengakses ViewModel

    @Inject
    lateinit var factory: ViewModelFactory
    // Properti untuk mengakses ViewModelFactory melalui Dependency Injection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        // Menyiapkan tampilan aktivitas menggunakan layout activity_home.xml

        setupToolbar()
        // Memanggil metode setupToolbar() untuk mengatur toolbar

        setupViewModel()
        // Memanggil metode setupViewModel() untuk menginisialisasi ViewModel

        setupNavigationController()
        // Memanggil metode setupNavigationController() untuk mengatur menu navigasi bawah
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this@HomeActivity,
            factory
        )[HomeViewModel::class.java]
        // Menginisialisasi ViewModel menggunakan ViewModelProvider dan ViewModelFactory
    }

    private fun setupToolbar() {
        supportActionBar?.elevation = 0F
        // Mengatur elevasi (elevation) toolbar
    }

    private fun setupNavigationController() {
        val navView: BottomNavigationView = findViewById(R.id.bottom_navbar)
        // Mendapatkan referensi ke BottomNavigationView dari layout

        val navController = findNavController(R.id.nav_host_fragment)
        // Mendapatkan NavController yang mengatur navigasi antar fragment

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_movie,
            R.id.navigation_tvshow,
            R.id.navigation_favorite
        ).build()
        // Konfigurasi AppBar untuk menentukan fragment-fragment yang memiliki toolbar

        setupActionBarWithNavController(navController, appBarConfiguration)
        // Menghubungkan ActionBar dengan NavController sesuai dengan konfigurasi

        navView.setupWithNavController(navController)
        // Menghubungkan BottomNavigationView dengan NavController
    }
}
