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
class PeticionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_peticion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var database = FirebaseDatabase.getInstance().reference

        super.onViewCreated(view, savedInstanceState)


        val con: Button = view.findViewById(R.id.con)
        con.setOnClickListener{
            view.findNavController().navigate(R.id.mapFragment)

            val diractivity:LoginActivity = activity as LoginActivity

            var material:Ventas = Ventas("", "", "", null, null, "", "", false, "")
            val nombre: EditText = view.findViewById(R.id.editText5)
            val desc: EditText = view.findViewById(R.id.editText4)
            val cantidad: EditText = view.findViewById(R.id.editText7)
            val pago: EditText=view.findViewById(R.id.editText8)

            val key = database.child("user").push().key
            material.id = key!!
            material.nombre = nombre.text.toString()
            material.descripcion = desc.text.toString()
            material.cantidad=cantidad.text.toString().toInt()
            material.pagounidad=pago.text.toString().toInt()
            material.userid=diractivity.Get()
            material.locationX = diractivity.getLocalizacionXM()
            material.locationY = diractivity.getLocalizacionYM()
            database.child("Ventas").child(key!!).setValue(material)

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
