package com.codangcoding.ilt10.presentation

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.codangcoding.ilt10.data.repository.PokemonRepository
import com.codangcoding.ilt10.data.repository.model.Pokemon
import com.codangcoding.ilt10.presentation.model.PokemonVO
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mockkStatic(Log::class)

    }

    @Test
    fun `on init should load pokemon`() = runBlockingTest {
        val repository = mockk<PokemonRepository> {
            coEvery { getPokemonList() } returns listOf(Pokemon("pikachu", "url"))
        }

        val viewModel = MainViewModel(repository)

        delay(100)
        assertThat(viewModel.pokemonList.value)
            .isEqualTo(listOf(PokemonVO("pikachu", "url")))
    }
}