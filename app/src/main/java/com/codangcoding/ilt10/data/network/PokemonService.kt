package com.codangcoding.ilt10.data.network

import com.codangcoding.ilt10.data.network.dto.Pagination
import com.codangcoding.ilt10.data.network.dto.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Pagination<PokemonResponse>
}