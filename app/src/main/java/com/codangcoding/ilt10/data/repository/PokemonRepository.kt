package com.codangcoding.ilt10.data.repository

import androidx.paging.Pager
import com.codangcoding.ilt10.data.db.entity.PokemonEntity

interface PokemonRepository {

    fun getPokemonDataPager(): Pager<Int, PokemonEntity>
}