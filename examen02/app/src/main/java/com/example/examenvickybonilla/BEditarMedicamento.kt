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

class BEditarMedicamento : AppCompatActivity() {
    val contenidoIntentExplicito = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result->
        if(result.resultCode == Activity.RESULT_OK){
            if (result.data != null){

            }
        }
    }
    var arreglo: ArrayList<BMedicamento> = ArrayList<BMedicamento> ()
    var idItemFarmaci=0
    //var idmed=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beditar_medicamento)
        val idMedic=intent.getIntExtra("idMedicament",0)
        val nombreMedic=BBaseDatos.TablaFarmacia!!.consultarMedicamentoPorId(idMedic).nombreM
       // val idFarma=intent.getIntExtra("idFarmacia",0)
        idItemFarmaci=intent.getIntExtra("idFarmacia",0)
        val nameMed=findViewById<EditText>(R.id.editNombreM)
        nameMed.setText(nombreMedic)
        val botonActualizarMeds=findViewById<Button>(R.id.btn_actualizar_m)
        botonActualizarMeds
            .setOnClickListener {
                BBaseDatos.TablaFarmacia!!.actualizarMedicamentoFormulario(nameMed.text.toString(),idMedic)
                abrirActividadParametros(AverMedicamentos::class.java)
            }
    }



    fun abrirActividadParametros(
        clase:Class<*>,
    ){
        val intentExplicito = Intent(this, clase)
        //intentExplicito.putExtra("nombreFarmacia", arreglo[idItemFarmaci].nombreF)
        intentExplicito.putExtra("idFarmacia",idItemFarmaci)
        contenidoIntentExplicito.launch(intentExplicito)
    }
}