package com.example.calculadoraimc

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class DatosIMC : AppCompatActivity() {

    var correoGlobal:String? = ""
    var imc = 0.00

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
        setContentView(R.layout.activity_datos_imc)

        val correo = intent.getStringExtra("correo")
        correoGlobal = correo
        val btnCalcularIMC = findViewById<Button>(R.id.btn_calcular_IMC)
        val alturaeditText = findViewById<EditText>(R.id.et_altura)
        val pesoeditText = findViewById<EditText>(R.id.et_peso)
        btnCalcularIMC.setOnClickListener {
            if(alturaeditText.text.isEmpty() || pesoeditText.text.isEmpty()){
                abrirDialogo("Uno o más campos vacíos")
            }else{
            val altura=alturaeditText.text.toString().toDouble()
            val peso=pesoeditText.text.toString().toDouble()
            val imctpm = peso/(altura*altura)
            imc = (imctpm * 100.0).roundToInt()/100.0
            guardarDatosIMC(correo,altura,peso,imc)
            abrirActividadParametros(ResultadoIMC::class.java)
        }
        }

        val btnRIrHistorial = findViewById<Button>(R.id.btn_d_ir_historial)
        btnRIrHistorial.setOnClickListener {
            abrirActividadParametros(HistoriaIMC::class.java)
        }

        val btnIMGDatos = findViewById<ImageButton>(R.id.btnimg_atras_datos)
        btnIMGDatos.setOnClickListener {
            abrirDialogoInicio()
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

    fun guardarDatosIMC(
        correo:String?,
        altura:Double,
        peso:Double,
        imc:Double
    ){
        val fechaformat = SimpleDateFormat("dd-MM-yyyy")
        val fecha = fechaformat.format(Date())
        val db = Firebase.firestore
        val imcRefUnico = db.collection("usuarios")
            .document("${correo}")
            .collection("historial")
        val datosIMC = hashMapOf(
            "fecha" to fecha,
            "altura" to altura,
            "peso" to peso,
            "imc" to imc
        )
        imcRefUnico.document(fecha.toString()).set(datosIMC)
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

    fun abrirDialogoInicio(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Seguro de regresar al inicio?" +
                "\nSi hace eso cerrará su sesión")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{
                    dialog, which ->
                abrirActividadParametros(Inicio::class.java)
            }
        )
        builder.setNegativeButton(
            "Cancelar",
            null
        )
        val dialog = builder.create()
        dialog.show()
    }
}