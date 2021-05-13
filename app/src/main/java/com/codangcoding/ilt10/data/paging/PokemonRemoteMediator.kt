package com.codangcoding.ilt10.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.codangcoding.ilt10.data.db.PokemonDb
import com.codangcoding.ilt10.data.db.entity.PokemonEntity
import com.codangcoding.ilt10.data.db.entity.PokemonMetaEntity
import com.codangcoding.ilt10.data.network.service.PokemonService
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class PokemonRemoteMediator(
    private val db: PokemonDb,
    private val service: PokemonService
) : RemoteMediator<Int, PokemonEntity>() {

    private val pokemonDao = db.pokemonDao()
    private val metaDao = db.metaDao()

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.SKIP_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val totalSize = db.withTransaction {
                        metaDao.meta()?.totalPokemon
                    } ?: 1
                    val currentSize = db.withTransaction {
                        pokemonDao.count()
                    }

                    if (currentSize == totalSize)
                        return MediatorResult.Success(endOfPaginationReached = true)

                    currentSize + 1
                }
            } ?: 1

            val response = service.getPokemonList(loadKey, state.config.pageSize)

            db.withTransaction {
                metaDao.insert(PokemonMetaEntity(response.count))
                pokemonDao.insertAll(response.results.map { PokemonEntity(it.name, it.url) })
            }

            return MediatorResult.Success(endOfPaginationReached = response.results.isEmpty())
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }
}