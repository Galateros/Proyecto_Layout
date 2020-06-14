package com.android.proyecto_layout

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.webkit.WebViewFragment
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import java.util.jar.Manifest

class LoginActivity : AppCompatActivity() {

    public var userid:String=""
    public var factoryid:String=""
    public var materialid:String=""
    public var localizationX = ""
    public var localizationY = ""





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //var nav = supportFragmentManager.findFragmentById(R.id.fragment2)



        Set()

        findNavController(R.id.fragment)

    }
    public fun setLocalizacion(x:String, y:String){
        localizationX = x
        localizationY = y
    }

    public fun getLocalizacionX():String{
        return localizationX
    }
    public fun getLocalizacionY():String{
        return localizationY
    }

    public fun Set (){
        userid = intent.getStringExtra("UserID")
        //println(userid+"---------------------------------------------------------------------------------")
    }

    public fun Get(): String{

        return userid
    }

    public fun Setfactory(valor: String){
        factoryid= valor
    }

    public fun Getfactory():String{
        return factoryid
    }

    public fun Setmaterial(valor: String){
        materialid= valor
    }

    public fun Getmaterial():String{
        return materialid
    }



    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
    public fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }

    }
}
