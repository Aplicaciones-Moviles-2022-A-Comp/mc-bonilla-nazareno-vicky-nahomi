package com.example.movcompvnbn2022a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.IdpResponse

class HFirebaseUIAuth : AppCompatActivity() {
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        if(res.resultCode== RESULT_OK){
            if (res.idpResponse!=null){
                this.seLogeo(res.idpResponse!!)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hfirebase_uiauth)
        val botonLogin=findViewById<Button>(R.id.btn_intent_firebase_ui)
        botonLogin
            .setOnClickListener {
                val providers= arrayListOf(AuthUI.IdpConfig.EmailBuilder().build())
                val signInIntent=AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .build()
                signInLauncher.launch(signInIntent)
            }
        val botonLogout=findViewById<Button>(R.id.btn_logout)
        botonLogout
            .setOnClickListener {
                AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener {
                        seDeslogeo()
                    }
            }

    }
    fun seLogeo(res: IdpResponse) {
        val botonLogin=findViewById<Button>(R.id.btn_intent_firebase_ui)
        val botonLogout=findViewById<Button>(R.id.btn_logout)
        botonLogin.visibility=View.INVISIBLE
        botonLogout.visibility=View.VISIBLE
    }
    fun seDeslogeo(){
        val botonLogin=findViewById<Button>(R.id.btn_intent_firebase_ui)
        val botonLogout=findViewById<Button>(R.id.btn_logout)
        botonLogin.visibility=View.VISIBLE
        botonLogout.visibility=View.INVISIBLE
    }
}