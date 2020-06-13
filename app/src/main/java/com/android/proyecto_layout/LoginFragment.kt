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
            val i = Intent(context, LoginActivity::class.java)
            startActivity(i)
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
