package com.example.movcompvnbn2022a

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import io.sentry.Sentry
import io.sentry.SentryLevel

class MainActivity : AppCompatActivity() {
    val CODIGO_RESPUESTA_INTENT_EXPLICITO=401
    /*val contenidoIntentExplicito= registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()){
        result ->
        if (result.resultCode== Activity.RESULT_OK){
            if (result.data!=null){
                val data=result.data
            }
        }
    }*/
    val contenidoIntentExplicito = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result->
        if(result.resultCode == Activity.RESULT_OK){
            if (result.data != null){
                val data = result.data
                Log.i("intent-epn","${data?.getStringExtra("nombreModificado")}")
            }
        }
    }


    val contenidoIntentImplicito = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result->
        if(result.resultCode == Activity.RESULT_OK){
            if (result.data != null){
                if (result.data!!.data !=null){
                    val uri: Uri=result.data!!.data!!
                    val cursor=contentResolver.query(
                        uri,
                        null,
                        null,
                        null,
                        null,
                        null
                    )
                    cursor?.moveToFirst()
                    val indiceTelefono=cursor?.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    )
                    val telefono= cursor?.getString(
                        indiceTelefono!!
                    )
                    cursor?.close()
                    Log.i("intent-epn","Telefono ${telefono}")
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Sentry.captureMessage("testing SDK setup",SentryLevel.INFO)
        //Sentry.captureMessage("ESTO ES UNA ADVERTENCIA",SentryLevel.WARNING)

        //BASE DE DATOS SQLITE
        EBaseDeDatos.TablaEntrenador = ESqliteHelperEntrenador(this)
        val botonACicloVida=findViewById<Button>(R.id.btn_ciclo_vida)
        botonACicloVida
            .setOnClickListener{
                irActividad(ACicloVida::class.java)
            }
        val botonActividadContador=findViewById<Button>(R.id.btn_a_contador)
        botonActividadContador
            .setOnClickListener{
                irActividad(Acontador2::class.java)
            }
        val botonListView=findViewById<Button>(R.id.btn_ir_list_view)
        botonListView
            .setOnClickListener{
                irActividad(BListView::class.java)
            }
        val botonIntent=findViewById<Button>(R.id.btn_intent)
        botonIntent
            .setOnClickListener {
                abrirActividadParametros(ClientExplicitoParametros::class.java)
            }

        val botoncrudentrenador=findViewById<Button>(R.id.btn_crud_entrenador)
        botoncrudentrenador
            .setOnClickListener {
                abrirActividadParametros(BCRUDEntrenador::class.java)
            }
        val botonRecyclerView=findViewById<Button>(R.id.btn_recycler_view)
        botonRecyclerView.setOnClickListener {
            irActividad(GRecyclerView::class.java)
        }

        val botonIrFirebase=findViewById<Button>(R.id.btn_ir_firebase)
        botonIrFirebase
            .setOnClickListener {
                irActividad(HFirebaseUIAuth::class.java)
            }

    }
    fun irActividad(
        clase: Class<*>
    ){
        val intent= Intent(this,clase)
        startActivity(intent) //definido en la clase AppCompatActivity() heredada
    }

    fun abrirActividadParametros(
        clase:Class<*>
    ){
        val intentExplicito = Intent(this, clase)
        intentExplicito.putExtra("nombre","Jean")
        intentExplicito.putExtra("apellido","Sartre")
        intentExplicito.putExtra("edad",83)
        intentExplicito.putExtra(
            "entrenadorPrincipal",
            BEntrenador(2,"Lisa","Rhee")
        )
        //ActivityResultLauncher
        startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }
}