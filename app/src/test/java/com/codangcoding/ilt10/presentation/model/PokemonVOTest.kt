package com.codangcoding.ilt10.presentation.model

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.Test

class PokemonVOTest {

    @Test
    fun `imageUrl should produce correct url`() {
        val vo = PokemonVO("Pikachu", "https://pokeapi.co/api/v2/pokemon/1/")

        val imageUrl = vo.imageUrl

        assertThat("https://pokeres.bastionbot.org/images/pokemon/1.png")
            .isEqualTo(imageUrl)
    }
}