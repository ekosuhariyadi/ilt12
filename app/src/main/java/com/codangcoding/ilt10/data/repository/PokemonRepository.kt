package com.codangcoding.ilt10.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.codangcoding.ilt10.data.db.PokemonDb
import com.codangcoding.ilt10.data.db.entity.PokemonEntity
import com.codangcoding.ilt10.data.network.service.PokemonService
import com.codangcoding.ilt10.data.paging.PokemonRemoteMediator
import com.codangcoding.ilt10.data.paging.PokemonRemotePagingSource
import com.codangcoding.ilt10.data.repository.model.Pokemon
import javax.inject.Inject

interface PokemonRepository {

    fun getPokemonDataPager(): Pager<Int, PokemonEntity>

    fun getRemotePokemonDataPager(): Pager<Int, Pokemon>
}

class DefaultPokemonRepository @Inject constructor(
    private val db: PokemonDb,
    private val service: PokemonService
) : PokemonRepository {

    @ExperimentalPagingApi
    override fun getPokemonDataPager(): Pager<Int, PokemonEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = PokemonRemoteMediator(
                db,
                service
            )
        ) {
            db.pokemonDao().pokemonListByPaging()
        }
    }

    override fun getRemotePokemonDataPager(): Pager<Int, Pokemon> {
        return Pager(
            config = PagingConfig(pageSize = 20)
        ) {
            PokemonRemotePagingSource(service)
        }
    }

}