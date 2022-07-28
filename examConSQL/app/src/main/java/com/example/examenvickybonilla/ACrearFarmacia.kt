package com.example.examenvickybonilla

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class ACrearFarmacia : AppCompatActivity() {

    //var arreglo: ArrayList<BFarmacia> = BBaseDatos.arregloFarmacia
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acrear_farmacia)

       /* val adaptador=ArrayAdapter(
            this, //contexto
            android.R.layout.simple_list_item_1,//como se va a ver XML
          //  arreglo
        )*/

        val botonAddFarmacia =findViewById<Button>(R.id.btn_add_farmacia)
        botonAddFarmacia
            .setOnClickListener {
               // anadirFarmacia(adaptador)
                //abrirDialogo()
                val nombreFarmacia=findViewById<EditText>(R.id.textNombreFarmacia)
                //BBaseDatos.TablaFarmacia!!.crearFarmacia(
               //     nombreFarmacia.text.to
                BBaseDatos.TablaFarmacia!!.crearFarmacia(
                    nombreFarmacia.text.toString()
                )
               // arreglo.add(
                //    BFarmacia(null,nombreFarmacia.text.toString())
               // )
                //adaptador.notifyDataSetChanged()
                abrirDialogo()
            }
       // adaptador.notifyDataSetChanged()
    }
    fun anadirFarmacia(
        adaptador: ArrayAdapter<BFarmacia>
    ){
        val nombreFarmacia=findViewById<EditText>(R.id.textNombreFarmacia)
       /* arreglo.add(
            BFarmacia(null,nombreFarmacia.text.toString())
        )
        adaptador.notifyDataSetChanged()*/
    }


    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Creado con éxito")
        builder.setPositiveButton(
            "Seguir creando",
            DialogInterface.OnClickListener { dialog, which ->
                val nombreFarmacia=findViewById<EditText>(R.id.textNombreFarmacia)
                nombreFarmacia.setText("")
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