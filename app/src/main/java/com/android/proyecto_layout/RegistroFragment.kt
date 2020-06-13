package com.android.proyecto_layout

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.security.MessageDigest

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [RegistroFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegistroFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       var database = FirebaseDatabase.getInstance().reference



        super.onViewCreated(view, savedInstanceState)
        val con: Button = view.findViewById(R.id.button)
        con.setOnClickListener{
            view.findNavController().navigate(R.id.loginFragment)

            fun hashWithAlgorithm(key: String): String {
                val digest = MessageDigest.getInstance("SHA-512")
                val bytes = digest.digest(key.toByteArray(Charsets.UTF_8))
                return bytes.fold("", { str, it -> str + "%02x".format(it) })
            }

            var user:User = User("", "", "")

            val email: EditText = view.findViewById(R.id.editText)

            val key = database.child("user").push().key
            user.id = key!!


            val pass: EditText = view.findViewById(R.id.editText2)
            user.email=email.text.toString()
            user.pass=hashWithAlgorithm(pass.text.toString())

            database.child("user").child(key!!).setValue(user)

            Log.d(ContentValues.TAG, pass.text.toString())
            Log.d(ContentValues.TAG, hashWithAlgorithm(pass.text.toString()))

        }


        val con2: Button = view.findViewById(R.id.register)
        con2.setOnClickListener{
            view.findNavController().navigate(R.id.loginFragment)




        }


    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegistroFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegistroFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
