package com.android.proyecto_layout

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    var userId:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    public fun setUser(id:String){
        userId = id
    }
    public fun getUser():String{
        return userId
    }




}
