package com.codangcoding.ilt10.di

import android.content.Context
import androidx.room.Room
import com.codangcoding.ilt10.data.db.PokemonDb
import com.codangcoding.ilt10.data.network.service.PokemonService
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    private val mapper: ObjectMapper by lazy {
        jacksonObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(JacksonConverterFactory.create(mapper))
            .build()
    }

    @Provides
    fun providePokemonServices(): PokemonService =
        retrofit.create(PokemonService::class.java)

    @Provides
    fun providePokemonDb(@ApplicationContext appContext: Context): PokemonDb =
        Room.databaseBuilder(appContext, PokemonDb::class.java, "pokemon.db")
            .build()
}
