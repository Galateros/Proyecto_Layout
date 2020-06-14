package com.android.proyecto_layout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
//import com.android.proyecto_layout.DespensaFirebase
import com.android.proyecto_layout.VentasRecyclerAdapter
import com.android.proyecto_layout.Ventas
import kotlinx.android.synthetic.main.fragment_ventas.*

/**
 * A simple [Fragment] subclass.
 */
class VentasFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ventas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val item: Button = view.findViewById(R.id.item)
        item.setOnClickListener{
            view.findNavController().navigate(R.id.mapFragment)
        }
        val produccion: Button = view.findViewById(R.id.produccion)
        produccion.setOnClickListener{
            view.findNavController().navigate(R.id.produccionFragment)
        }
        val ventas: Button = view.findViewById(R.id.ventas)
        ventas.setOnClickListener{
            //view.findNavController().navigate(R.id.ventasFragment)
        }
        val perfil: Button = view.findViewById(R.id.perfil)
        perfil.setOnClickListener{
            view.findNavController().navigate(R.id.perfilFragment)
        }
        super.onViewCreated(view, savedInstanceState)
        list_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = VentasRecyclerAdapter(mutableListOf<Ventas>())
        }
        getProducts()
    }



    public fun getProducts(){
        val ref = FirebaseDatabase.getInstance().getReference("/Ventas")
        val diractivity:LoginActivity = activity as LoginActivity
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                val list = mutableListOf<Ventas>()
                p0.children.forEach {
                    val product = it.getValue(Ventas::class.java)
                    if(product!!.userid == diractivity.Get()){
                        list.add(product!!)
                    }

                }
                if (view != null){
                    list_recycler_view.adapter = VentasRecyclerAdapter(list)
                }

            }


        } )
    }



}
