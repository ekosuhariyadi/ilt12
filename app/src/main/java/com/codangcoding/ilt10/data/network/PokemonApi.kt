package com.codangcoding.ilt10.data.network

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object PokemonApi {

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

    val pokemonService: PokemonService by lazy {
        retrofit.create(PokemonService::class.java)
    }
}