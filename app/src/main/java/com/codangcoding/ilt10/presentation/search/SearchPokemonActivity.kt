package com.codangcoding.ilt10.presentation.search

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.codangcoding.ilt10.databinding.ActivitySearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import reactivecircus.flowbinding.android.widget.textChanges
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

@AndroidEntryPoint
class SearchPokemonActivity : AppCompatActivity() {

    private val viewModel by viewModels<SearchViewModel>()

    @FlowPreview
    @ExperimentalTime
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            etKeyword.textChanges()
                .skipInitialValue()
                .debounce(500.toDuration(DurationUnit.MILLISECONDS))
                .filter { it.length >= 3 }
                .onEach { name ->
                    viewModel.search(name.toString())
                }
                .launchIn(lifecycleScope)

            val adapter = SearchPokemonVOAdapter {}
            rvPokemon.adapter = adapter

            viewModel.stateFlow.asLiveData()
                .observe(this@SearchPokemonActivity) { uiState ->
                    loading.visibility = View.GONE
                    when (uiState) {
                        is SearchUIState.Success -> adapter.submitList(uiState.list)
                        is SearchUIState.Loading -> {
                            notFound.visibility = View.GONE
                            loading.visibility = View.VISIBLE
                        }
                        is SearchUIState.NotFound -> notFound.visibility = View.VISIBLE
                        else -> {
                            // no-op
                        }
                    }
                }
        }
    }
}