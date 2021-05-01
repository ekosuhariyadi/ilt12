package com.codangcoding.ilt10

import androidx.multidex.MultiDexApplication
import com.codangcoding.ilt10.data.db.PokemonDb

class PokemonApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        PokemonDb.initialize(this)
    }
}