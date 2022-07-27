package com.example.bachelor_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bachelor_app.ui.fragment.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, LoginFragment().apply { arguments = intent.extras }, null).commit()
        }
    }
}