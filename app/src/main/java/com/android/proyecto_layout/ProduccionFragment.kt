package com.android.proyecto_layout

import android.os.Bundle
import android.util.Log
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
//import edu.daec.otrouber.modelo.DespensaFirebase
import com.android.proyecto_layout.FabricaRecyclerAdapter
import com.android.proyecto_layout.Fabrica
import kotlinx.android.synthetic.main.fragment_produccion.*


/**
 * A simple [Fragment] subclass.
 */
class ProduccionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_produccion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item: Button = view.findViewById(R.id.item)
        item.setOnClickListener{
            view.findNavController().navigate(R.id.mapFragment)
        }
        val produccion: Button = view.findViewById(R.id.produccion)
        produccion.setOnClickListener{
            //view.findNavController().navigate(R.id.produccionFragment)
        }
        val ventas: Button = view.findViewById(R.id.ventas)
        ventas.setOnClickListener{
            view.findNavController().navigate(R.id.ventasFragment)
        }
        val perfil: Button = view.findViewById(R.id.perfil)
        perfil.setOnClickListener{
            view.findNavController().navigate(R.id.perfilFragment)
        }


        list_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = FabricaRecyclerAdapter(mutableListOf<Fabrica>())
        }
        getProducts()
    }
    public fun getProducts(){
        val ref = FirebaseDatabase.getInstance().getReference("/Fabrica")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                val list = mutableListOf<Fabrica>()
                p0.children.forEach {
                    val product = it.getValue(Fabrica::class.java)
                    list.add(product!!)

                }
                list_recycler_view.adapter = FabricaRecyclerAdapter(list)
            }


        } )
    }


}
