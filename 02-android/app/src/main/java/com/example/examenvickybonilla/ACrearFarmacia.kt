package com.example.examenvickybonilla

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class ACrearFarmacia : AppCompatActivity() {
    var arreglo: ArrayList<BFarmacia> = BBaseDatos.arregloFarmacia
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acrear_farmacia)

        val adaptador=ArrayAdapter(
            this, //contexto
            android.R.layout.simple_list_item_1,//como se va a ver XML
            arreglo
        )

        val botonAddFarmacia =findViewById<Button>(R.id.btn_add_farmacia)
        botonAddFarmacia
            .setOnClickListener {
                anadirNumero(adaptador)
                abrirDialogo()
            }
        adaptador.notifyDataSetChanged()
    }
    fun anadirNumero(
        adaptador: ArrayAdapter<BFarmacia>
    ){
        arreglo.add(
            BFarmacia("Cruz Azul",null, null)
        )
        adaptador.notifyDataSetChanged()
    }
    /*override fun onResume() {
        super.onResume()
        val adaptador=ArrayAdapter(
            this,
            android.R.layout.simple_expandable_list_item_1,
            arreglo
        )
        adaptador.notifyDataSetChanged()
    }*/

    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Creado con éxito")
        builder.setPositiveButton(
            "¿Seguir creando?",
            DialogInterface.OnClickListener { dialog, which ->
            }
        )
        builder.setNegativeButton(
            "Regresar a Farmacias",
            DialogInterface.OnClickListener { dialog, which ->

                irActividad(MainActivity::class.java)
            }
        )
        val dialogo = builder.create()
        dialogo.show()

    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent= Intent(this,clase)
        startActivity(intent) //definido en la clase AppCompatActivity() heredada
    }
}