package com.codangcoding.ilt10.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object {

        lateinit var INSTANCE: PokemonDb
            private set

        fun initialize(context: Context) {
            if (this::INSTANCE.isInitialized) return

            INSTANCE = Room.databaseBuilder(context, PokemonDb::class.java, "pokemon.db")
                .build()
        }
    }
}