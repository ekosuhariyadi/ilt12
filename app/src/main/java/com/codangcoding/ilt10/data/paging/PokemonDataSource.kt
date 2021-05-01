package com.codangcoding.ilt10.data.paging

import androidx.paging.PositionalDataSource
import com.codangcoding.ilt10.data.db.dao.PokemonDao
import com.codangcoding.ilt10.data.db.dao.PokemonMetaDao
import com.codangcoding.ilt10.data.db.entity.PokemonEntity
import com.codangcoding.ilt10.data.db.entity.PokemonMetaEntity
import com.codangcoding.ilt10.data.network.PokemonService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonDataSource(
    private val scope: CoroutineScope,
    private val pokemonDao: PokemonDao,
    private val metaDao: PokemonMetaDao,
    private val service: PokemonService,
) : PositionalDataSource<PokemonEntity>() {

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<PokemonEntity>
    ) {
        scope.launch {
            withContext(Dispatchers.IO) {
                val meta = metaDao.meta()
                val fromDb = pokemonDao.pagedPokemonList()
                if (fromDb.isEmpty()) {
                    val response = service.getPokemonList()

                    response.results
                        .map { PokemonEntity(it.name, it.url) }
                        .also {
                            pokemonDao.insertAll(it)
                            metaDao.insert(PokemonMetaEntity(response.count))
                        }
                        .let { callback.onResult(it, 0, response.count) }
                } else {
                    callback.onResult(fromDb, 0, meta.totalPokemon)
                }
            }
        }
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<PokemonEntity>) {
        scope.launch {
            withContext(Dispatchers.IO) {
                val fromDb = pokemonDao.pagedPokemonList(params.startPosition, params.loadSize)
                if (fromDb.isEmpty()) {
                    service.getPokemonList(params.startPosition, params.loadSize)
                        .results
                        .map { PokemonEntity(it.name, it.url) }
                        .also(pokemonDao::insertAll)
                        .let(callback::onResult)
                } else {
                    callback.onResult(fromDb)
                }
            }
        }
    }
}