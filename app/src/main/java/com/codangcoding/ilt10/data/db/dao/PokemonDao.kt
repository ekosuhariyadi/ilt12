package com.codangcoding.ilt10.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.codangcoding.ilt10.data.db.entity.PokemonEntity

@Dao
interface PokemonDao {

    @Insert
    fun insertAll(entities: List<PokemonEntity>)

    @Query("SELECT * FROM pokemon LIMIT :offset , :limit")
    fun pagedPokemonList(offset: Int = 0, limit: Int = 20): List<PokemonEntity>
}