package com.example.calculadoraimc

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HistoriaIMC : AppCompatActivity() {

    var arreglo = ArrayList<IMCData>()
    var correoGlobal:String? = ""
    var imc = 0.00
    lateinit var rvHistorial:RecyclerView

    val contenidoIntentExplicito = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result->
        if(result.resultCode == Activity.RESULT_OK){
            if (result.data != null){
                val data = result.data
                //Log.i("intent-epn","${data?.getStringExtra("nombreModificado")}")
            }
        }
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historia_imc)
        requestedOrientation= ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val correo = intent.getStringExtra("correo")
        correoGlobal=correo
        rvHistorial = findViewById<RecyclerView>(R.id.rv_historial)
        cargarHistorial(rvHistorial,correo)
        val btnVerGrafica = findViewById<Button>(R.id.btn_ver_grafica)
        btnVerGrafica.setOnClickListener {
            abrirActividadParametros(GraficaIMC::class.java)
        }
        val btnImgHistorial = findViewById<ImageButton>(R.id.btnimg_atras_historial)
        btnImgHistorial.setOnClickListener {
            abrirActividadParametros(DatosIMC::class.java)
        }
    }

    fun inicializarRV(
        lista:ArrayList<IMCData>,
        recyclerView: RecyclerView
    ){
        val adaptador = AdaptadorHistorial(
            this,
            lista,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }

    fun cargarHistorial(
        recyclerView: RecyclerView,
        correo:String?
    ){
        val db = Firebase.firestore
        val imcRefUnico = db
            .collection("usuarios")
            .document("${correo}")
            .collection("historial")

        imcRefUnico
            .get()
            .addOnSuccessListener {
                arreglo = ArrayList<IMCData>()
                for (imcitem in it) {
                    aniadirIMC(arreglo, imcitem)
                }
                inicializarRV(arreglo,recyclerView)
            }

    }

    fun aniadirIMC(
        arregloNuevo: ArrayList<IMCData>,
        imc: QueryDocumentSnapshot
    ) {
        arregloNuevo.add(
            IMCData(
                imc.get("fecha") as String?,
                imc.get("imc") as Double?,
                imc.get("peso") as Double?,
                imc.get("altura") as Double?
            )
        )
    }

    fun abrirActividadParametros(
        clase:Class<*>
    ){
        val intentExplicito = Intent(this, clase)
        intentExplicito.putExtra("correo",correoGlobal)
        intentExplicito.putExtra("imc", imc)
        contenidoIntentExplicito.launch(intentExplicito)
    }

}