package com.android.proyecto_layout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import com.google.firebase.database.FirebaseDatabase

/**
 * A simple [Fragment] subclass.
 */
class SetFactoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_factory, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var database = FirebaseDatabase.getInstance().reference
        super.onViewCreated(view, savedInstanceState)


        val con: Button = view.findViewById(R.id.con)
        con.setOnClickListener{
            view.findNavController().navigate(R.id.stadisticFragment)
            val diractivity:LoginActivity = activity as LoginActivity

            var factory:Fabrica = Fabrica("", "", "", "","", "")
            val nombre: EditText = view.findViewById(R.id.editText9)
            val desc: EditText = view.findViewById(R.id.descripcion)

            val key = database.child("Fabrica").push().key
            factory.id = key!!
            factory.nombre = nombre.text.toString()
            factory.descripcion = desc.text.toString()
            factory.userid= diractivity.Get()
            factory.locationX = diractivity.getLocalizacionX()
            factory.locationY = diractivity.getLocalizacionY()

            diractivity.Setfactory(factory.id.toString())

            database.child("Fabrica").child(key!!).setValue(factory)

            database.child("FabricaSt").child(key!!).setValue(factory)

        }



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
            view.findNavController().navigate(R.id.ventasFragment)
        }
        val perfil: Button = view.findViewById(R.id.perfil)
        perfil.setOnClickListener{
            view.findNavController().navigate(R.id.perfilFragment)
        }
    }
}
