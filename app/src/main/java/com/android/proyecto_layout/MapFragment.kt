package com.android.proyecto_layout

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*

/**
 * A simple [Fragment] subclass.
 */
class MapFragment : Fragment(),OnMapReadyCallback{

    private lateinit var googleMap: GoogleMap

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
        peticion.setOnClickListener{
            view.findNavController().navigate(R.id.peticionFragment)
        }
        val nfabrica: Button = view.findViewById(R.id.factory)
        nfabrica.setOnClickListener{
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
            googleMap.setOnMarkerClickListener { marker ->
                if (marker.isInfoWindowShown) {
                    marker.hideInfoWindow()
                    Log.v("WAAAAAAGH","-------------------MarkerClick----------------------------")
                } else {
                    marker.showInfoWindow()
                    Log.v("WAAAAAAGH","-------------------MarkerClick2----------------------------")
                    view?.findNavController()?.navigate(R.id.infoFactoryFragment)
                }
                true
            }
        }


    }

}
