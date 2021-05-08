package com.codangcoding.ilt10.presentation.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codangcoding.ilt10.databinding.ActivityLoginBinding
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable

class LoginActivity : AppCompatActivity() {

    private val disposableBag = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            Observable.combineLatest(
                etUsername.textChanges().skipInitialValue().map(CharSequence::toString),
                etPassword.textChanges().skipInitialValue().map(CharSequence::toString),
                { username, password ->
                    username.isNotEmpty() && password.isNotEmpty()
                }
            ).subscribe { enabled ->
                btnLogin.isEnabled = enabled
            }.let { disposableBag.add(it) }
        }
    }

    override fun onDestroy() {
        disposableBag.dispose()
        super.onDestroy()
    }
}