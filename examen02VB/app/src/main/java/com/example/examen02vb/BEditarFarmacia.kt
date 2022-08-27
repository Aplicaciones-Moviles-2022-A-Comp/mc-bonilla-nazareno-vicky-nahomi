package com.example.examen02vb


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

class BEditarFarmacia : AppCompatActivity() {
    //var arreglo: ArrayList<BFarmacia> = BBaseDatos.arregloFarmacia
    var arreglo: ArrayList<BFarmacia> = ArrayList<BFarmacia> ()
    var idItemFarmacia=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beditar_farmacia)
        val idFa=intent.getIntExtra("idFarmacia",0)

        val nombreFarm=findViewById<EditText>(R.id.editNombreF)

        val botonActualizar=findViewById<Button>(R.id.btn_actualizar_f)
        botonActualizar
            .setOnClickListener {

                irActividad(MainActivity::class.java)
            }
    }
    fun irActividad(
        clase: Class<*>
    ){
        val intent= Intent(this,clase)
        startActivity(intent) //definido en la clase AppCompatActivity() heredada
    }
    fun getIDTablaF(farmacia :BFarmacia): String{
        return ""+farmacia.idF
    }
}