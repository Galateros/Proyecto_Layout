package com.android.proyecto_layout

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

/**
 * A simple [Fragment] subclass.
 */
class StadisticFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_s, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val op1: Button = view.findViewById(R.id.op1)

        val refLAct: LoginActivity = activity as LoginActivity
        val id = refLAct.Getfactory()

        val per1 : TextView = view.findViewById(R.id.textView24)
        val per2 : TextView = view.findViewById(R.id.textView25)
        val per3 : TextView = view.findViewById(R.id.textView26)

        val ref = FirebaseDatabase.getInstance().getReference("/FabricaSt/" + id)
        val listener = object : ValueEventListener
        {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    if(postSnapshot.key.toString() == "mat1"){
                        per1.text = postSnapshot.getValue().toString()+"%"
                    }

                    if(postSnapshot.key.toString() == "mat2"){
                        per2.text = postSnapshot.getValue().toString()+"%"
                    }
                    if(postSnapshot.key.toString() == "mat3"){
                        per3.text = postSnapshot.getValue().toString()+"%"
                    }

                    println(postSnapshot.value)

                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(ContentValues.TAG, "loadPost:onCancelled", databaseError.toException())
                // ...
            }

        }
        ref.addValueEventListener(listener)





        op1.setOnClickListener{
            view.findNavController().navigate(R.id.infoFactoryFragment)
        }
        val op2: Button = view.findViewById(R.id.op2)
        op2.setOnClickListener{
            view.findNavController().navigate(R.id.infoFactoryFragment)
        }
        val op3: Button = view.findViewById(R.id.op3)
        op3.setOnClickListener{
            view.findNavController().navigate(R.id.infoFactoryFragment)
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
