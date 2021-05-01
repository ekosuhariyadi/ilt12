package com.codangcoding.ilt10.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonEntity(
    val name: String,
    @PrimaryKey val url: String
)