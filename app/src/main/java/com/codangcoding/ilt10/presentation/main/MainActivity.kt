package com.codangcoding.ilt10.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.codangcoding.ilt10.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PokemonVOAdapter {}
        with(binding) {
            rvPokemon.adapter = adapter
        }

        viewModel.pokemonList.observe(this@MainActivity) { pokemonList ->
            adapter.submitData(lifecycle, pokemonList)
        }
    }
}