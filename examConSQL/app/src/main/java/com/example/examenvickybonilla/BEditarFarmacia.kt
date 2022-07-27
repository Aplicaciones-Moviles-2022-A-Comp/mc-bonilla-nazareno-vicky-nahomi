package com.example.examenvickybonilla

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class BEditarFarmacia : AppCompatActivity() {
    var arreglo: ArrayList<BFarmacia> = BBaseDatos.arregloFarmacia
    var idItemFarmacia=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beditar_farmacia)
        val idFa=intent.getIntExtra("idFarmacia",0)
        val nombreFa=intent.getStringExtra("nombreFarmacia")
        idItemFarmacia=idFa
        val nombreFarm=findViewById<EditText>(R.id.editNombreF)
        nombreFarm.setText(nombreFa)
        val botonActualizar=findViewById<Button>(R.id.btn_actualizar_f)
        botonActualizar
            .setOnClickListener {
                arreglo[idItemFarmacia].nombreF= nombreFarm.text.toString()
                irActividad(MainActivity::class.java)
            }
    }
    fun irActividad(
        clase: Class<*>
    ){
        val intent= Intent(this,clase)
        startActivity(intent) //definido en la clase AppCompatActivity() heredada
    }

}