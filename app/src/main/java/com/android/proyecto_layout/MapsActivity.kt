package com.android.proyecto_layout

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sumesa = LatLng(19.3575519, -99.1625910)
        mMap.addMarker(MarkerOptions().position(sumesa).title("Sumesa Coyoacan"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sumesa))
        val superama = LatLng(19.3588798, -99.1680898)
        mMap.addMarker(MarkerOptions().position(superama).title("Superama Coyoacan"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(superama))
        val laComer = LatLng(19.3449751, -99.1721433)
        mMap.addMarker(MarkerOptions().position(laComer).title("La Comer Coyoacan"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(laComer))
        val wallmart = LatLng(19.3447255, -99.1811934)
        mMap.addMarker(MarkerOptions().position(wallmart).title("Wallmart MiguelAngel"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(wallmart))
        val superama2 = LatLng(19.3581678, -99.1514806)
        mMap.addMarker(MarkerOptions().position(superama2).title("Superama Churubusco"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(superama2))


    }




}
