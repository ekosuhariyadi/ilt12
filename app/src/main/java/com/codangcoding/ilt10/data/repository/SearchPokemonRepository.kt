package com.codangcoding.ilt10.data.repository

import com.codangcoding.ilt10.data.db.PokemonDb
import com.codangcoding.ilt10.data.repository.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface SearchPokemonRepository {

    fun getPokemonByName(name: String): Flow<List<Pokemon>>
}

class DefaultSearchPokemonRepository @Inject constructor(
    private val db: PokemonDb
) : SearchPokemonRepository {

    override fun getPokemonByName(name: String): Flow<List<Pokemon>> {
        return db.pokemonDao().pokemonListByName("%$name%")
            .map { list -> list.map { Pokemon(it.name, it.url) } }
    }

}