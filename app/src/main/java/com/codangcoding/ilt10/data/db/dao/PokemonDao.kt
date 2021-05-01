package com.codangcoding.ilt10.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.codangcoding.ilt10.data.db.entity.PokemonEntity

@Dao
interface PokemonDao {

    @Insert
    suspend fun insertAll(entities: List<PokemonEntity>)

    @Query("SELECT COUNT(name) FROM pokemon")
    suspend fun count(): Int

    @Query("SELECT * FROM pokemon")
    fun pokemonListByPaging(): PagingSource<Int, PokemonEntity>

}