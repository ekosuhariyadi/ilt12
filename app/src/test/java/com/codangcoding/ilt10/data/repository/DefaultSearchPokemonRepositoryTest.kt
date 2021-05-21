package com.codangcoding.ilt10.data.repository

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.codangcoding.ilt10.data.db.PokemonDb
import com.codangcoding.ilt10.data.db.entity.PokemonEntity
import com.codangcoding.ilt10.data.repository.model.Pokemon
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class DefaultSearchPokemonRepositoryTest {

    private val db = mockk<PokemonDb>()

    private val repository =
        DefaultSearchPokemonRepository(db)

    @Test
    fun `getPokemonByName should return value on success`() = runBlockingTest {
        every { db.pokemonDao() } returns mockk {
            every {
                pokemonListByName(any())
            } returns flowOf(
                listOf(
                    PokemonEntity("bulbasaur", "url")
                )
            )
        }

        val result = repository.getPokemonByName("bulb")
            .first()

        assertThat(
            listOf(
                Pokemon("bulbasaur", "url")
            )
        ).isEqualTo(result)
    }
}