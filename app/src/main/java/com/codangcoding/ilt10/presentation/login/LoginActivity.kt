package com.codangcoding.ilt10.presentation.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.codangcoding.ilt10.databinding.ActivityLoginBinding
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import reactivecircus.flowbinding.android.widget.textChanges

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            etUsername.textChanges()
                .skipInitialValue()
                .map(CharSequence::toString)
                .combine(
                    etPassword.textChanges()
                        .skipInitialValue()
                        .map(CharSequence::toString)
                ) { username, password ->
                    username.isNotEmpty() && password.isNotEmpty()
                }
                .onEach { enabled ->
                    btnLogin.isEnabled = enabled
                }
                .launchIn(lifecycleScope)
        }
    }
}