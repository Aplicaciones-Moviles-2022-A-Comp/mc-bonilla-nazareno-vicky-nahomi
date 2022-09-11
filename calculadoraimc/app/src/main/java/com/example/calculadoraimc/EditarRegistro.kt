package com.example.calculadoraimc

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class EditarRegistro : AppCompatActivity() {

    var correoGlobal:String?=""
    var imc = 0.00
    var fechaGlobal:String?=""

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
        setContentView(R.layout.activity_editar_registro)
        val correo = intent.getStringExtra("correo")
        correoGlobal = correo
        val btnimgAtrasEditar = findViewById<ImageButton>(R.id.btnimg_atras_editar)
        btnimgAtrasEditar.setOnClickListener {
            abrirActividadParametros(HistoriaIMC::class.java)
        }
        val fecha = intent.getStringExtra("fecha")
        val tvFecha = findViewById<TextView>(R.id.tv_fecha_editar)
        tvFecha.text = fecha
        fechaGlobal=fecha
        val btnCalcularIMC = findViewById<Button>(R.id.btn_actualizar_IMC)
        val alturaeditText = findViewById<EditText>(R.id.et_altura_actualizar)
        val pesoeditText = findViewById<EditText>(R.id.et_peso_actualizar)
        btnCalcularIMC.setOnClickListener {
            if (alturaeditText.text.isEmpty() || pesoeditText.text.isEmpty()) {
                abrirDialogo("Uno o más campos vacíos")
            } else {
                val altura = alturaeditText.text.toString().toDouble()
                val peso = pesoeditText.text.toString().toDouble()
                val imctpm = peso / (altura * altura)
                imc = (imctpm * 100.0).roundToInt() / 100.0
                guardarDatosIMC(correo, altura, peso, imc)
                abrirActividadParametros(ResultadoIMC::class.java)
            }
        }
    }

    fun abrirActividadParametros(
        clase:Class<*>
    ){
        val intentExplicito = Intent(this, clase)
        intentExplicito.putExtra("correo",correoGlobal)
        intentExplicito.putExtra("imc", imc)
        contenidoIntentExplicito.launch(intentExplicito)
    }

    fun abrirDialogo(Titulo:String){
            val builder = AlertDialog.Builder(this)
            builder.setTitle(Titulo)
            builder.setPositiveButton(
                "Aceptar",
                null
            )

            val dialog = builder.create()
            dialog.show()
    }

    fun guardarDatosIMC(
        correo:String?,
        altura:Double,
        peso:Double,
        imc:Double
    ){
        val db = Firebase.firestore
        val imcRefUnico = db.collection("usuarios")
            .document("${correo}")
            .collection("historial")
        val datosIMC = hashMapOf(
            "fecha" to fechaGlobal,
            "altura" to altura,
            "peso" to peso,
            "imc" to imc
        )
        imcRefUnico.document(fechaGlobal.toString()).set(datosIMC)
    }
}