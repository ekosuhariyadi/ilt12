package com.codangcoding.ilt10.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.codangcoding.ilt10.data.db.PokemonDb
import com.codangcoding.ilt10.data.network.PokemonApi
import com.codangcoding.ilt10.data.paging.PokemonDataSourceFactory
import com.codangcoding.ilt10.data.repository.PokemonRepository
import com.codangcoding.ilt10.presentation.model.PokemonVO

class MainViewModel : ViewModel() {

    val pokemonList: LiveData<PagedList<PokemonVO>> by lazy {
        repository.getPokemonDataSourceFactory()
            .map { PokemonVO(it.name, it.url) }
            .toLiveData(20)
    }
    private val repository: PokemonRepository

    init {
        repository = object : PokemonRepository {
            override fun getPokemonDataSourceFactory(): PokemonDataSourceFactory {
                return PokemonDataSourceFactory(
                    viewModelScope,
                    PokemonDb.INSTANCE.pokemonDao(),
                    PokemonDb.INSTANCE.metaDao(),
                    PokemonApi.pokemonService
                )
            }
        }
    }

}