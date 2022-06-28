package com.example.movcompvnbn2022a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Acontador2 : AppCompatActivity() {

    var contador=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acontador2)


        val botonIncrementar=findViewById<Button>(R.id.btn_incrementar)
        val displaycuenta=findViewById<TextView>(R.id.contadorsillo)

        botonIncrementar
            .setOnClickListener{
                contador++
                displaycuenta.text=contador.toString()
            }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run{
            //guardar variables/primitivos
            putString("textoGuardado",contador.toString())

        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val textoRecuperado:String?=savedInstanceState.getString("textoGuardado")
        if (textoRecuperado!=null){
            contador=textoRecuperado.toInt()
            //papas con  cuero
        }

    }

    override fun onResume() {
        super.onResume()
        val displaycuenta = findViewById<TextView>(R.id.contadorsillo)
        displaycuenta.text = contador.toString()
    }
}