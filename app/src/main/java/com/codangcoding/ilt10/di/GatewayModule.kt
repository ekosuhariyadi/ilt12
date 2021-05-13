package com.codangcoding.ilt10.di

import com.codangcoding.ilt10.data.repository.DefaultPokemonRepository
import com.codangcoding.ilt10.data.repository.DefaultSearchPokemonRepository
import com.codangcoding.ilt10.data.repository.PokemonRepository
import com.codangcoding.ilt10.data.repository.SearchPokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GatewayModule {

    @Binds
    abstract fun bindSearchPokemonRepository(
        repository: DefaultSearchPokemonRepository
    ): SearchPokemonRepository

    @Binds
    abstract fun bindPokemonRepository(
        repository: DefaultPokemonRepository
    ): PokemonRepository
}