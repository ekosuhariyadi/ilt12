package com.codangcoding.ilt10.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.liveData
import androidx.paging.map
import com.codangcoding.ilt10.data.repository.PokemonRepository
import com.codangcoding.ilt10.data.repository.SearchPokemonRepository
import com.codangcoding.ilt10.presentation.model.PokemonVO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: SearchPokemonRepository
) : ViewModel() {

    private val _pokemonList = MutableStateFlow<List<PokemonVO>>(emptyList())
    val pokemonList: StateFlow<List<PokemonVO>> = _pokemonList

    fun search(name: String) {
        viewModelScope.launch {
            repository.getPokemonByName(name)
                .map { list -> list.map { PokemonVO(it.name, it.url) } }
                .collect { pokemons ->
                    _pokemonList.value = pokemons
                }
        }
    }
}