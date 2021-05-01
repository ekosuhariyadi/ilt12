package com.codangcoding.ilt10.data.repository

import com.codangcoding.ilt10.data.paging.PokemonDataSourceFactory

interface PokemonRepository {

    fun getPokemonDataSourceFactory(): PokemonDataSourceFactory

}