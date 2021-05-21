package com.codangcoding.ilt10.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codangcoding.ilt10.data.repository.SearchPokemonRepository
import com.codangcoding.ilt10.presentation.model.PokemonVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchPokemonRepository
) : ViewModel() {

    private val _stateFlow = MutableStateFlow<SearchUIState>(SearchUIState.None)
    val stateFlow: StateFlow<SearchUIState> = _stateFlow

    fun search(name: String) {
        viewModelScope.launch {
            _stateFlow.value = SearchUIState.Loading
            repository.getPokemonByName(name)
                .map { list -> list.map { PokemonVO(it.name, it.url) } }
                .collect { pokemons ->
                    _stateFlow.value = if (pokemons.isNotEmpty())
                        SearchUIState.Success(pokemons)
                    else
                        SearchUIState.NotFound
                }
        }
    }
}

sealed class SearchUIState {

    object None : SearchUIState()
    object Loading : SearchUIState()
    object NotFound : SearchUIState()
    data class Success(val list: List<PokemonVO>) : SearchUIState()
}