package com.codangcoding.ilt10.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.codangcoding.ilt10.databinding.ActivityMainBinding

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

        viewModel.pokemonList.observe(this) { pokemonList ->
            adapter.submitList(pokemonList)
        }
    }
}