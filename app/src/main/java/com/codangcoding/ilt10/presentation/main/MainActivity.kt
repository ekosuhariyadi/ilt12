package com.codangcoding.ilt10.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.codangcoding.ilt10.data.db.PokemonDb
import com.codangcoding.ilt10.data.db.entity.PokemonEntity
import com.codangcoding.ilt10.data.network.PokemonApi
import com.codangcoding.ilt10.data.paging.PokemonRemoteMediator
import com.codangcoding.ilt10.data.paging.PokemonRemotePagingSource
import com.codangcoding.ilt10.data.repository.PokemonRepository
import com.codangcoding.ilt10.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    @ExperimentalPagingApi
    private val viewModel by viewModels<MainViewModel> {
        MainViewModelFactory(
            object : PokemonRepository {
                override fun getPokemonDataPager(): Pager<Int, PokemonEntity> {
                    return Pager(
                        config = PagingConfig(pageSize = 20),
                        remoteMediator = PokemonRemoteMediator(
                            PokemonDb.INSTANCE,
                            PokemonApi.pokemonService
                        )
                    ) {
                        PokemonDb.INSTANCE.pokemonDao().pokemonListByPaging()
                    }
                }

                override fun getRemotePokemonDataPager(): Pager<Int, PokemonEntity> {
                    return Pager(
                        config = PagingConfig(pageSize = 20)
                    ) {
                        PokemonRemotePagingSource(PokemonApi.pokemonService)
                    }
                }
            }
        )
    }

    @ExperimentalPagingApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PokemonVOAdapter {}
        with(binding) {
            rvPokemon.adapter = adapter
        }

        viewModel.remotePokemonList.observe(this@MainActivity) { pokemonList ->
            adapter.submitData(lifecycle, pokemonList)
        }
    }
}