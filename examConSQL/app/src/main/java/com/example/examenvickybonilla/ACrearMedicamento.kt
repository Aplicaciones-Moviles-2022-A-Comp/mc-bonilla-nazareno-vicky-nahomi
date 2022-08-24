package com.example.examenvickybonilla

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog

class ACrearMedicamento : AppCompatActivity() {

    val contenidoIntentExplicito = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result->
        if(result.resultCode == Activity.RESULT_OK){
            if (result.data != null){

            }
        }
    }
    //var arreglo: ArrayList<BFarmacia> = BBaseDatos.arregloFarmacia
    var idItemFarmaci=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acrear_medicamento)
        val idFarma=intent.getIntExtra("idFarmacia",0)
        idItemFarmaci=idFarma
        val nuevaMed=findViewById<EditText>(R.id.textNombreMed)
        val botonCrearMeds=findViewById<Button>(R.id.btn_add_medicamento)
        botonCrearMeds
            .setOnClickListener {
                val nombreMed=nuevaMed.text.toString()
                BBaseDatos.TablaFarmacia!!.crearMedicamento(
                    nombreMed,
                    idItemFarmaci
                )
                abrirDialogo()
            }
    }
    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Creado con éxito")
        builder.setPositiveButton(
            "Seguir creando",
            DialogInterface.OnClickListener { dialog, which ->
                val nuevaMed=findViewById<EditText>(R.id.textNombreMed)
                nuevaMed.setText("")
            }
        )
        builder.setNegativeButton(
            "volver a  ${ BBaseDatos.TablaFarmacia!!.consultarfarmaciaPorId(idItemFarmaci).nombreF}",
            DialogInterface.OnClickListener { dialog, which ->
                abrirActividadParametros(AverMedicamentos::class.java)
            }
        )
        val dialogo = builder.create()
        dialogo.show()

    }
    fun abrirActividadParametros(
        clase:Class<*>,
    ){
        val intentExplicito = Intent(this, clase)
        intentExplicito.putExtra("idFarmacia",idItemFarmaci)
        contenidoIntentExplicito.launch(intentExplicito)
    }


    fun irActividad(
        clase: Class<*>
    ){
        val intent= Intent(this,clase)
        startActivity(intent) //definido en la clase AppCompatActivity() heredada
    }


}