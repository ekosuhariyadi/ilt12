package com.codangcoding.ilt10.presentation.model

data class PokemonVO(
    val name: String,
    val url: String
) {

    val imageUrl: String
        get() {
            val index = url.split("/").dropLast(1).last()
            return "https://pokeres.bastionbot.org/images/pokemon/$index.png"
        }
}
