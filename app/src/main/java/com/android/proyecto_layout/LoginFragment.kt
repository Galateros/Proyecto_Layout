package com.android.proyecto_layout

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.json.JSONObject
import java.security.MessageDigest

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val login: Button = view.findViewById(R.id.entrar)
        login.setOnClickListener{
            var username: EditText = view.findViewById(R.id.editText)
            var pass: EditText = view.findViewById(R.id.editText2)

            var database:DatabaseReference = FirebaseDatabase.getInstance().reference
            database.child("user").addValueEventListener(object : ValueEventListener {

                fun hashWithAlgorithm(key: String): String {
                    val digest = MessageDigest.getInstance("SHA-512")
                    val bytes = digest.digest(key.toByteArray(Charsets.UTF_8))
                    return bytes.fold("", { str, it -> str + "%02x".format(it) })
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (postSnapshot in dataSnapshot.children) {

                        //println("Snapshot "+ postSnapshot.value)
                        val js = JSONObject(postSnapshot.value.toString())
                        if (js.get("email") == username.text.toString()){
                            val password = hashWithAlgorithm(pass.text.toString())
                            if(js.get("pass") == password){
                                println("The Password is True");
                                val i = Intent(context, LoginActivity::class.java)
                                val id:String = js.get("id") as String
                                i.putExtra("UserID",id)
                                startActivity(i)
                            }else{
                                println("The Password is False");
                            }
                        }
                        println(js.get("email"))

                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    println("loadPost:onCancelled: " + databaseError.toException())

                }
            })
            val i = Intent(context, LoginActivity::class.java)
            //startActivity(i)
        }

        val register: Button = view.findViewById(R.id.register)
        register.setOnClickListener{
           view.findNavController().navigate(R.id.registroFragment2)




        }









        /* val text1: EditText = view.findViewById(R.id.editText)

         val actionCodeSettings = ActionCodeSettings.newBuilder()
             // URL you want to redirect back to. The domain (www.example.com) for this
             // URL must be whitelisted in the Firebase Console.
             .setUrl("https://www.example.com/finishSignUp?cartId=1234")
             // This must be true
             .setHandleCodeInApp(true)
             //.setIOSBundleId("com.example.ios")
             .setAndroidPackageName(
                 "com.android.proyecto_layout",
                 true, /* installIfNotAvailable */
                 "21" /* minimumVersion */)
             .build()
         val auth = FirebaseAuth.getInstance()
         auth.sendSignInLinkToEmail(text1.toString(), actionCodeSettings)
             .addOnCompleteListener { task ->
                 if (task.isSuccessful) {
                     Log.d(TAG, "Email sent.")
                 }
             }*/





    }
}
