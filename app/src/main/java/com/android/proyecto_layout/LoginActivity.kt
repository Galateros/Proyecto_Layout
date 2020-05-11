package com.android.proyecto_layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewFragment
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //var nav = supportFragmentManager.findFragmentById(R.id.fragment2)

        findNavController(R.id.fragment)

    }
}
