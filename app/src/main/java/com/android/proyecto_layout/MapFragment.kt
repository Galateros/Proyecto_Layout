package com.android.proyecto_layout

import android.content.ContentValues
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_map.*
import org.json.JSONObject
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class MapFragment : Fragment(),OnMapReadyCallback{

    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        map_view.onCreate(savedInstanceState)
        map_view.onResume()
        map_view.getMapAsync(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_map, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val peticion: Button = view.findViewById(R.id.petition)

        val diractivity:LoginActivity = activity as LoginActivity






        peticion.setOnClickListener{
            view.findNavController().navigate(R.id.peticionFragment)

        }

        val nfabrica: Button = view.findViewById(R.id.factory)
        nfabrica.setOnClickListener{
            // almacenar localización



            val localizationX = diractivity.getLocalizacionX()
            val localizationY = diractivity.getLocalizacionY()

            view.findNavController().navigate(R.id.setFactoryFragment)
        }


        val item: Button = view.findViewById(R.id.item)
        item.setOnClickListener{
            //view.findNavController().navigate(R.id.mapFragment)
        }
        val produccion: Button = view.findViewById(R.id.produccion)
        produccion.setOnClickListener{
            view.findNavController().navigate(R.id.produccionFragment)
        }
        val ventas: Button = view.findViewById(R.id.ventas)
        ventas.setOnClickListener{
            view.findNavController().navigate(R.id.ventasFragment)
        }
        val perfil: Button = view.findViewById(R.id.perfil)
        perfil.setOnClickListener{
            view.findNavController().navigate(R.id.perfilFragment)
        }
    }
    override fun onMapReady(map: GoogleMap?) {
        map?.let {
            googleMap = it

            val cdmx = LatLng(19.432608, -99.133209)
            googleMap.addMarker(
                MarkerOptions()
                    .position(cdmx)
                    .title("CDMX"))

            googleMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    cdmx,
                    14f
                )
            )

            val diractivity:LoginActivity = activity as LoginActivity
            val ref = FirebaseDatabase.getInstance().getReference("/Fabrica/")
            val listener = object : ValueEventListener
            {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (postSnapshot in dataSnapshot.children) {

                       // val js = (postSnapshot.value.toString())
                       // println(js)
                        val fabricas = LatLng(postSnapshot.child("/locationX").getValue().toString().toDouble(), postSnapshot.child("/locationY").getValue().toString().toDouble())

                        googleMap.addMarker(
                            MarkerOptions()
                                .position(fabricas)
                                .title(postSnapshot.child("/id").getValue().toString())
                        )

                        googleMap.setOnMarkerClickListener { marker ->

                            if (marker.isInfoWindowShown) {
                                marker.hideInfoWindow()
                                Log.v("WAAAAAAGH","-------------------MarkerClick----------------------------")
                            } else {
                                marker.showInfoWindow()
                                Log.v("WAAAAAAGH","-------------------MarkerClick2----------------------------")
                                diractivity.Setfactory(marker.title)
                                //println(diractivity.Getfactory())
                                view?.findNavController()?.navigate(R.id.infoFactoryFragment)
                            }
                            true
                        }



                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    // Getting Post failed, log a message
                    Log.w(ContentValues.TAG, "loadPost:onCancelled", databaseError.toException())
                    // ...
                }

            }
            ref.addValueEventListener(listener)





            val ref2 = FirebaseDatabase.getInstance().getReference("/Ventas/")
            val listener2 = object : ValueEventListener
            {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (postSnapshot in dataSnapshot.children) {

                        // val js = (postSnapshot.value.toString())
                        // println(js)
                        val materiales = LatLng(postSnapshot.child("/locationX").getValue().toString().toDouble(), postSnapshot.child("/locationY").getValue().toString().toDouble())

                        googleMap.addMarker(
                            MarkerOptions()
                                .position(materiales)
                                .title(postSnapshot.child("/id").getValue().toString())
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                        )

                        googleMap.setOnMarkerClickListener { marker ->
                            if (marker.isInfoWindowShown) {
                                marker.hideInfoWindow()
                                Log.v("WAAAAAAGH","-------------------MarkerClick----------------------------")
                            } else {
                                marker.showInfoWindow()
                                Log.v("WAAAAAAGH","-------------------MarkerClick2----------------------------")
                                diractivity.Setmaterial(marker.title)
                                view?.findNavController()?.navigate(R.id.infoMaterialFragment)
                            }
                            true
                        }

                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    // Getting Post failed, log a message
                    Log.w(ContentValues.TAG, "loadPost:onCancelled", databaseError.toException())
                    // ...
                }

            }
            ref2.addValueEventListener(listener2)


        }
        val refLAct: LoginActivity = activity as LoginActivity
        refLAct.setUpMap()
        val diractivity:LoginActivity = activity as LoginActivity
        this.fusedLocationClient = LocationServices.getFusedLocationProviderClient(refLAct)

        // 1
        map?.isMyLocationEnabled = true

// 2
        fusedLocationClient.lastLocation.addOnSuccessListener() { location ->
            // Got last known location. In some rare situations this can be null.
            // 3
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                map?.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
                diractivity.setLocalizacion(location.latitude.toString(),location.longitude.toString())
                diractivity.setLocalizacionM(location.latitude.toString(),location.longitude.toString())
            }
        }

    }





}
