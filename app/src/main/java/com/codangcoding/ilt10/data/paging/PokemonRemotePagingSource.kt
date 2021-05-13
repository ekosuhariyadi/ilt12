package com.codangcoding.ilt10.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.codangcoding.ilt10.data.db.entity.PokemonEntity
import com.codangcoding.ilt10.data.network.service.PokemonService
import com.codangcoding.ilt10.data.repository.model.Pokemon
import retrofit2.HttpException
import java.io.IOException

class PokemonRemotePagingSource(
    private val pokemonService: PokemonService
) : PagingSource<Int, Pokemon>() {

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            val offset = params.key ?: 0
            val response = pokemonService.getPokemonList(offset, params.loadSize)

            val data = response.results
                .map { Pokemon(it.name, it.url) }
            LoadResult.Page(data, params.key, offset + params.loadSize)
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}