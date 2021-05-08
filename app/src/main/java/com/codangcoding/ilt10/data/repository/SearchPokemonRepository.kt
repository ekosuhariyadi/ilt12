package com.codangcoding.ilt10.data.repository

import com.codangcoding.ilt10.data.db.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

interface SearchPokemonRepository {

    fun getPokemonByName(name: String): Flow<List<PokemonEntity>>
}