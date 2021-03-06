package com.codangcoding.ilt10.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codangcoding.ilt10.databinding.ActivityHomeBinding
import com.codangcoding.ilt10.presentation.login.LoginActivity
import com.codangcoding.ilt10.presentation.main.MainActivity
import com.codangcoding.ilt10.presentation.search.SearchPokemonActivity

class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {

            btnPokemon.setOnClickListener {
                val intent = Intent(this@HomeActivity, MainActivity::class.java)
                startActivity(intent)
            }

            btnLogin.setOnClickListener {
                val intent = Intent(this@HomeActivity, LoginActivity::class.java)
                startActivity(intent)
            }

            btnSearch.setOnClickListener {
                val intent = Intent(this@HomeActivity, SearchPokemonActivity::class.java)
                startActivity(intent)
            }
        }
    }
}