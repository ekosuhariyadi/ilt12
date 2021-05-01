package com.codangcoding.ilt10.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codangcoding.ilt10.data.db.entity.PokemonMetaEntity

@Dao
interface PokemonMetaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(meta: PokemonMetaEntity)

    @Query("SELECT * FROM pokemon_meta")
    fun meta(): PokemonMetaEntity
}