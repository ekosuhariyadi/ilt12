package com.codangcoding.ilt10.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.liveData
import androidx.paging.map
import com.codangcoding.ilt10.data.repository.PokemonRepository
import com.codangcoding.ilt10.presentation.model.PokemonVO

class MainViewModel(
    repository: PokemonRepository
) : ViewModel() {

    val pokemonList = repository.getPokemonDataPager()
        .liveData
        .map { pagingData ->
            pagingData.map { PokemonVO(it.name, it.url) }
        }
        .cachedIn(viewModelScope)

}