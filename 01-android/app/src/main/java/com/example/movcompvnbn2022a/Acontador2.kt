package com.example.movcompvnbn2022a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Adapter
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



    override fun onPause() {
        super.onPause()
        //cuando cambia posición
        val displaycuenta = findViewById<TextView>(R.id.contadorsillo)
      //  contador=displaycuenta.text.toString().toInt()
        displaycuenta.text = contador.toString()
    }
    override fun onResume() {

        super.onResume()
        //esto pasa cuando sales de la app
        val displaycuenta = findViewById<TextView>(R.id.contadorsillo)
        displaycuenta.text = contador.toString()
        //displaycuenta.text="999"
    }



    override fun onRestart() {
        super.onRestart()
        val displaycuenta = findViewById<TextView>(R.id.contadorsillo)
        displaycuenta.text = contador.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        val displaycuenta = findViewById<TextView>(R.id.contadorsillo)
        displaycuenta.text = contador.toString()
    }

}