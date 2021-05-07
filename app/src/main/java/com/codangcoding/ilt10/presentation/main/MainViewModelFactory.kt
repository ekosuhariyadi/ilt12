package com.codangcoding.ilt10.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codangcoding.ilt10.data.repository.PokemonRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val repository: PokemonRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}