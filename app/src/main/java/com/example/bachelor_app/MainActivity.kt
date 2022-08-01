package com.example.bachelor_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.bachelor_app.databinding.ActivityMainBinding
import com.example.bachelor_app.ui.activities.LoginScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        binding.loginButton.setOnClickListener {
            val intent = Intent(this, LoginScreen::class.java).apply {
                putExtra(IS_LOGIN, true)
            }
            startActivity(intent)
        }
        binding.signUpButton.setOnClickListener {
            val intent = Intent(this, LoginScreen::class.java).apply {
                putExtra(IS_LOGIN, false)
            }
            startActivity(intent)
        }
    }

    companion object {
        const val IS_LOGIN = "isLogin"
    }
}