package com.codangcoding.ilt10.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.liveData
import androidx.paging.map
import com.codangcoding.ilt10.data.repository.PokemonRepository
import com.codangcoding.ilt10.presentation.model.PokemonVO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    repository: PokemonRepository
) : ViewModel() {

    val pokemonList = repository.getPokemonDataPager()
        .liveData
        .map { pagingData ->
            pagingData.map { PokemonVO(it.name, it.url) }
        }
        .cachedIn(viewModelScope)

    val remotePokemonList = repository.getRemotePokemonDataPager()
        .liveData
        .map { pagingData ->
            pagingData.map { PokemonVO(it.name, it.url) }
        }
        .cachedIn(viewModelScope)

}