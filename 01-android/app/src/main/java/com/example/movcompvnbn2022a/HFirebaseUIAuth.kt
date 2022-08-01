package com.example.movcompvnbn2022a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app

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

        if (res.isNewUser==true){
            registrarUsuarioPorPrimeraVez(res)
        }
    }
    fun registrarUsuarioPorPrimeraVez(
        usuario: IdpResponse
    ){
        val usuarioLogeado=FirebaseAuth.getInstance().currentUser
        if (usuario.email!=null && usuarioLogeado !=null) {
            val db = Firebase.firestore
            val roles = arrayListOf("usuario")
            val email = usuario.email
            val uid = usuario
            val nuevoUsuario = hashMapOf<String, Any>(
                "roles" to roles,
                "uid" to uid.toString(),
                "email" to email.toString()
            )
            db.collection(email.toString())
                .document(email.toString())
                .set(nuevoUsuario)
                .addOnSuccessListener {
                    //se creo el usuario en firestore
                }
                .addOnFailureListener {
            //hubieron errores
                }
        }
    }
    fun seDeslogeo(){
        val botonLogin=findViewById<Button>(R.id.btn_intent_firebase_ui)
        val botonLogout=findViewById<Button>(R.id.btn_logout)
        botonLogin.visibility=View.VISIBLE
        botonLogout.visibility=View.INVISIBLE
    }
}