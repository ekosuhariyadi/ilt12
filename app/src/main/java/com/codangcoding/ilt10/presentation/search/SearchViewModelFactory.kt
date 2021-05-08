package com.codangcoding.ilt10.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codangcoding.ilt10.data.repository.PokemonRepository
import com.codangcoding.ilt10.data.repository.SearchPokemonRepository

@Suppress("UNCHECKED_CAST")
class SearchViewModelFactory(
    private val repository: SearchPokemonRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(repository) as T
    }
}