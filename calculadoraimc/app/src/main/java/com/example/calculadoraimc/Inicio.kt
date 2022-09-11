package com.example.calculadoraimc

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.IdpResponse
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class Inicio : AppCompatActivity() {

    var correoGlobal:String? = ""

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res: FirebaseAuthUIAuthenticationResult ->
        if(res.resultCode== RESULT_OK){
            if (res.idpResponse!=null){
                this.seLogeo(res.idpResponse!!)
            }
        }
    }

    val contenidoIntentExplicito = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result->
        if(result.resultCode == Activity.RESULT_OK){
            if (result.data != null){
                val data = result.data
                //Log.i("intent-epn","${data?.getStringExtra("nombreModificado")}")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSignin = findViewById<Button>(R.id.btn_signinF)
        btnSignin.setOnClickListener {
           val providers = arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build())
            // Create and launch sign-in intent
            val signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()
            signInLauncher.launch(signInIntent)
        }
        addDatosInic()

    }

    fun seLogeo(
        res: IdpResponse
    ){
        if(res.isNewUser==true){
            registarusuarioPrimeraVez(res)
            abrirActividadParametros(DatosIMC::class.java)
        }else{
            correoGlobal = res.email
            val usuarioLogeado = FirebaseAuth.getInstance().currentUser
            abrirActividadParametros(DatosIMC::class.java)
        }
    }

    fun registarusuarioPrimeraVez(
        usuario: IdpResponse
    ){
        val usuarioLogeado = FirebaseAuth.getInstance().currentUser
        if(usuario.email != null && usuarioLogeado != null){
            val db = Firebase.firestore
            val roles = arrayListOf("usuario")
            val email = usuario.email
            val uid = usuarioLogeado.uid
            val nuevoUsuario = hashMapOf<String,Any>(
                "roles" to roles,
                "uid" to uid,
                "email" to email.toString()
            )
            correoGlobal = usuario.email
            db.collection("usuarios").document(email.toString())
                .set(nuevoUsuario).addOnSuccessListener {
                }.addOnFailureListener{
                }
        }
    }

    fun addDatosInic(){

        val db = Firebase.firestore
        val imcRefUnico = db.collection("usuarios")
            .document("mail@mail.com")
            .collection("historial")
        val datosIMC = hashMapOf(
            "fecha" to "31-08-2022",
            "altura" to 1.86,
            "peso" to 108.0,
            "imc" to 31.22
        )
        imcRefUnico.document("31-08-2022").set(datosIMC)
        val datos2IMC = hashMapOf(
            "fecha" to "01-09-2022",
            "altura" to 1.86,
            "peso" to 83.20,
            "imc" to 24.05
        )
        imcRefUnico.document("01-09-2022").set(datos2IMC)
        val datos3IMC = hashMapOf(
            "fecha" to "02-09-2022",
            "altura" to 1.86,
            "peso" to 81.0,
            "imc" to 23.41
        )
        imcRefUnico.document("02-09-2022").set(datos3IMC)
        val datos4IMC = hashMapOf(
            "fecha" to "03-09-2022",
            "altura" to 1.86,
            "peso" to 82.7,
            "imc" to 23.9
        )
        imcRefUnico.document("03-09-2022").set(datos4IMC)
        val datos5IMC = hashMapOf(
            "fecha" to "04-09-2022",
            "altura" to 1.86,
            "peso" to 83.2,
            "imc" to 24.05
        )
        imcRefUnico.document("04-09-2022").set(datos5IMC)
        val datos6IMC = hashMapOf(
            "fecha" to "05-09-2022",
            "altura" to 1.86,
            "peso" to 82.0,
            "imc" to 23.45
        )
        imcRefUnico.document("05-09-2022").set(datos6IMC)
        val datos7IMC = hashMapOf(
            "fecha" to "06-09-2022",
            "altura" to 1.86,
            "peso" to 84.0,
            "imc" to 24.04
        )
        imcRefUnico.document("06-09-2022").set(datos7IMC)
        val datos8IMC = hashMapOf(
            "fecha" to "07-09-2022",
            "altura" to 1.86,
            "peso" to 88.8,
            "imc" to 25.39
        )
        imcRefUnico.document("07-09-2022").set(datos8IMC)
        val datos9IMC = hashMapOf(
            "fecha" to "08-09-2022",
            "altura" to 1.86,
            "peso" to 82.0,
            "imc" to 23.45
        )
        imcRefUnico.document("08-09-2022").set(datos9IMC)
        val datos10IMC = hashMapOf(
            "fecha" to "09-09-2022",
            "altura" to 1.86,
            "peso" to 84.8,
            "imc" to 24.51
        )
        imcRefUnico.document("09-09-2022").set(datos10IMC)
        val datos11IMC = hashMapOf(
            "fecha" to "10-09-2022",
            "altura" to 1.86,
            "peso" to 62.00,
            "imc" to 17.73
        )
        imcRefUnico.document("10-09-2022").set(datos11IMC)
    }

    fun abrirActividadParametros(
        clase:Class<*>
    ){
        val intentExplicito = Intent(this, clase)
        intentExplicito.putExtra("correo",correoGlobal)
        contenidoIntentExplicito.launch(intentExplicito)
    }

}