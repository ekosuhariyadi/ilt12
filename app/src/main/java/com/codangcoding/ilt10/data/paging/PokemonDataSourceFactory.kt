package com.codangcoding.ilt10.data.paging

import androidx.paging.DataSource
import com.codangcoding.ilt10.data.db.dao.PokemonDao
import com.codangcoding.ilt10.data.db.dao.PokemonMetaDao
import com.codangcoding.ilt10.data.db.entity.PokemonEntity
import com.codangcoding.ilt10.data.network.PokemonService
import kotlinx.coroutines.CoroutineScope

class PokemonDataSourceFactory(
    private val scope: CoroutineScope,
    private val pokemonDao: PokemonDao,
    private val metaDao: PokemonMetaDao,
    private val service: PokemonService
) : DataSource.Factory<Int, PokemonEntity>() {

    override fun create(): DataSource<Int, PokemonEntity> {
        return PokemonDataSource(scope, pokemonDao, metaDao, service)
    }
}