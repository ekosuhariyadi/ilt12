package com.codangcoding.ilt10.presentation.search

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.codangcoding.ilt10.data.repository.SearchPokemonRepository
import com.codangcoding.ilt10.data.repository.model.Pokemon
import com.codangcoding.ilt10.presentation.model.PokemonVO
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class SearchViewModelTest {

    private val repository = mockk<SearchPokemonRepository>()

    private val viewModel = SearchViewModel(repository)

    @Test
    fun `search should emit Success state when data found`() = runBlockingTest {
        every {
            repository.getPokemonByName(any())
        } returns flowOf(
            listOf(
                Pokemon("bulbasaur", "url")
            )
        )

        viewModel.search("bulb")

        viewModel.stateFlow.test {
            assertThat(
                SearchUIState.Success(
                    listOf(
                        PokemonVO("bulbasaur", "url")
                    )
                )
            ).isEqualTo(expectItem())
        }
    }

    @Test
    fun `search should emit NotFound state when data isEmpty`() = runBlockingTest {
        every {
            repository.getPokemonByName(any())
        } returns flowOf(emptyList())

        viewModel.search("bulb")

        viewModel.stateFlow.test {
            assertThat(SearchUIState.NotFound).isEqualTo(expectItem())
        }

    }
}