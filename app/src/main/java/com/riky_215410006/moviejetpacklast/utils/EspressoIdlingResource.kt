package com.riky_215410006.moviejetpacklast.utils

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    // Nama unik untuk sumber daya idling
    private const val RESOURCE = "GLOBAL"

    // Membuat instance dari CountingIdlingResource
    val espressoTestIdlingResource = CountingIdlingResource(RESOURCE)

    // Fungsi untuk menambah hitungan sumber daya idling
    fun increment() {
        espressoTestIdlingResource.increment()
    }

    // Fungsi untuk mengurangi hitungan sumber daya idling
    fun decrement() {
        espressoTestIdlingResource.decrement()
    }
}
