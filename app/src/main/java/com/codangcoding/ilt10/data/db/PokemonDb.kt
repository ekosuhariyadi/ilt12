package com.codangcoding.ilt10.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codangcoding.ilt10.data.db.dao.PokemonDao
import com.codangcoding.ilt10.data.db.dao.PokemonMetaDao
import com.codangcoding.ilt10.data.db.entity.PokemonEntity
import com.codangcoding.ilt10.data.db.entity.PokemonMetaEntity

@Database(
    entities = [
        PokemonEntity::class,
        PokemonMetaEntity::class,
    ],
    version = 1
)
abstract class PokemonDb : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    abstract fun metaDao(): PokemonMetaDao

}