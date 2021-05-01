package com.codangcoding.ilt10.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_meta")
data class PokemonMetaEntity(
    @PrimaryKey
    val totalPokemon: Int
)