package com.example.calculadoraimc

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class ResultadoIMC : AppCompatActivity() {

    var correoGlobal:String? = ""

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
        setContentView(R.layout.activity_resultado_ic)
        val correo = intent.getStringExtra("correo")
        correoGlobal=correo
        val resultado = intent.getDoubleExtra("imc",0.00)
        val resultadoTextView = findViewById<TextView>(R.id.tv_resultadoIMC)
        resultadoTextView.text = resultado.toString()
        val mensajeTextView = findViewById<TextView>(R.id.tv_mensaje_imc)
        if(resultado<18.5){
            mensajeTextView.text = getText(R.string.bajo)
            mensajeTextView.setTextColor(getColor(R.color.blue))
        }
        if(resultado>=18.5 && resultado<25){
            mensajeTextView.text = getText(R.string.normal)
            mensajeTextView.setTextColor(getColor(R.color.green))
        }
        if(resultado>=25 && resultado<30){
            mensajeTextView.text = getText(R.string.sobrepeso)
            mensajeTextView.setTextColor(getColor(R.color.yellow))
        }
        if(resultado>=30){
            mensajeTextView.text = getText(R.string.obesidad)
            mensajeTextView.setTextColor(getColor(R.color.red))
        }

        val btnRIrHistorial = findViewById<Button>(R.id.btn_r_ir_historial)
        btnRIrHistorial.setOnClickListener {
            abrirActividadParametros(HistoriaIMC::class.java)
        }

        val btnIMGResultado = findViewById<ImageButton>(R.id.btnimg_atras_resultado)
        btnIMGResultado.setOnClickListener {
            abrirActividadParametros(DatosIMC::class.java)
        }

    }

    fun abrirActividadParametros(
        clase:Class<*>
    ){
        val intentExplicito = Intent(this, clase)
        intentExplicito.putExtra("correo",correoGlobal)
        //intentExplicito.putExtra("imc", imc)
        contenidoIntentExplicito.launch(intentExplicito)
    }
}