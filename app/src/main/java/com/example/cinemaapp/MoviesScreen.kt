package com.example.cinemaapp

import android.app.Activity
import android.os.Bundle
import com.example.cinemaapp.databinding.ActivityMoviesScreenBinding

class MoviesScreen : Activity() {

    private lateinit var binding: ActivityMoviesScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMoviesScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}