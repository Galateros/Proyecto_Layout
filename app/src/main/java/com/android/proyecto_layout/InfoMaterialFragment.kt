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

/**
 * A simple [Fragment] subclass.
 */
class InfoMaterialFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_material, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item: Button = view.findViewById(R.id.item)
        var database = FirebaseDatabase.getInstance().reference



        val refLAct: LoginActivity = activity as LoginActivity
        val id = refLAct.Getmaterial()
        val descr: TextView = view.findViewById(R.id.textView16)
        val material: TextView = view.findViewById(R.id.textView18)
        val cantidad : TextView = view.findViewById(R.id.textView20)
        val resolver: Button = view.findViewById(R.id.resolver)
        var userid: String = ""
        resolver.visibility = View.GONE

        val ref = FirebaseDatabase.getInstance().getReference("/Ventas/" + id)
        var rest : Boolean = false

        fun checkresolver(iduser: String, idactualuser: String, res:Boolean){

            println("user id" + iduser + "other user id" + idactualuser + "resolver" + res)

            if(iduser != idactualuser && res == false){
                resolver.visibility = View.VISIBLE
            }
            if(iduser == idactualuser || res == true){
                resolver.visibility = View.GONE
            }


        }



        val listener = object : ValueEventListener
        {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {

                    //println("Snapshot "+ postSnapshot.value)
                    //val js = JSONObject(postSnapshot.value.toString())

                    if(postSnapshot.key.toString() == "descripcion"){
                        descr.text = postSnapshot.getValue().toString()
                    }
                    if(postSnapshot.key.toString()=="nombre"){
                        material.text = postSnapshot.getValue().toString()
                    }
                    if(postSnapshot.key.toString()=="cantidad"){
                        cantidad.text = postSnapshot.getValue().toString()
                    }
                    if(postSnapshot.key.toString()=="userid"){
                        userid= postSnapshot.getValue().toString()
                        println("useeeeer"+postSnapshot.getValue().toString())
                        println("us" + userid)
                    }
                    if(postSnapshot.key.toString() == "resuelto"){
                       rest = postSnapshot.getValue().toString().toBoolean()
                    }
                    println(postSnapshot)
                    checkresolver(userid, refLAct.Get(), rest)

                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(ContentValues.TAG, "loadPost:onCancelled", databaseError.toException())
                // ...
            }

        }

        ref.addValueEventListener(listener)


        resolver.setOnClickListener {
            rest = true
            database.child("Ventas").child(id).child("resuelto").setValue(rest)

        }


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
