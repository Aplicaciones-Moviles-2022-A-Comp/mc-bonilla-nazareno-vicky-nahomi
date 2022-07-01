package com.example.movcompvnbn2022a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ClientExplicitoParametros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_explicito_parametros)
        val nombre=intent.getStringExtra("nombre")
        val apellido=intent.getStringExtra("apellido")
        val edad=intent.getIntExtra("edad",0)
        val boton=findViewById<Button>(R.id.btn_devolver_respuesta)
        boton
            .setOnClickListener { devolverRespuesta() }
    }

    fun devolverRespuesta(){
        val intentDevolverParametros=Intent()
        intentDevolverParametros.putExtra("nombreModificado","Lola")
        intentDevolverParametros.putExtra("edadModificado",12)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }
}