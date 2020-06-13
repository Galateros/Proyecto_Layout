package com.android.proyecto_layout

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

        //val jsonGet = URL("https://189.147.92.77:25565/factories/" + id).readText()
        //val jsonPost = URL("https://189.147.92.77:25565/factories").readText()
        //println(jsonPost)



      /* fun sendPostRequest(id: String,name:String, locationX:String, locationY:String) { //id name locationX locationY

            var reqParam = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8")
            reqParam += "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8")
            reqParam += "&" + URLEncoder.encode("locationX", "UTF-8") + "=" + URLEncoder.encode(locationX, "UTF-8")
            reqParam += "&" + URLEncoder.encode("locationY", "UTF-8") + "=" + URLEncoder.encode(locationY, "UTF-8")

            val mURL = URL("https://189.147.92.77:25565/factories")

            with(mURL.openConnection() as HttpURLConnection) {
                // optional default is GET
                requestMethod = "POST"

                val wr = OutputStreamWriter(getOutputStream());
                wr.write(reqParam);
                wr.flush();

                println("URL : $url")
                println("Response Code : $responseCode")

                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    println("Response : $response")
                }
            }
        }*/

        val ref = FirebaseDatabase.getInstance().getReference("/Ventas/" + id)
        val listener = object : ValueEventListener
        {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    if(postSnapshot.key.toString() == "nombre"){
                        var name = postSnapshot.getValue().toString()
                    }

                    if(postSnapshot.key.toString() == "locationX"){
                        var locationX = postSnapshot.getValue().toString()
                    }
                    if(postSnapshot.key.toString() == "locationY"){
                        var locationY = postSnapshot.getValue().toString()
                    }

                    println(postSnapshot)

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
