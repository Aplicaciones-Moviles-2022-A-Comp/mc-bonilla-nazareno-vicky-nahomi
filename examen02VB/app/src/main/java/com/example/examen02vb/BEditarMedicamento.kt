package com.example.examen02vb


import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class BEditarMedicamento : AppCompatActivity() {
    val contenidoIntentExplicito = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result->
        if(result.resultCode == Activity.RESULT_OK){
            if (result.data != null){
                val data=result.data
            }
        }
    }

    var idItemFarmaci: Long=0
    var idmed: Long=0
    var nombreF=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beditar_medicamento)
        idmed=intent.getLongExtra("idMedicamento",0)
        idItemFarmaci=intent.getLongExtra("idFarmacia",0)
        nombreF=intent.getStringExtra("nombreF").toString()
        val nameMed=findViewById<EditText>(R.id.editNombreM)
        nameMed.setText(intent.getStringExtra("nombreM"))
        val botonActualizarMeds=findViewById<Button>(R.id.btn_actualizar_m)
        botonActualizarMeds
            .setOnClickListener {
                editMeds(nameMed.text.toString(),idmed)
                nameMed.setText("")
                abrirActividadParametros(AverMedicamentos::class.java)
            }
    }
    fun editMeds(nombreMed: String, idmeds: Long){
        val db = Firebase.firestore
        val medRefUnico=db.collection("examen02")
            .document("${idItemFarmaci}")
            .collection("Medicamentos")

        val dataMeds= hashMapOf(
            "idMedicamento" to idmeds,
            "nombreM" to nombreMed
        )
        medRefUnico.document(idmeds.toString()).set(dataMeds)

    }


    fun abrirActividadParametros(
        clase:Class<*>,
    ){
        val intentExplicito = Intent(this, clase)
        intentExplicito.putExtra("idMedicamento", idmed)
        intentExplicito.putExtra("idFarmacia",idItemFarmaci)
        intentExplicito.putExtra("nombreF",nombreF)

        contenidoIntentExplicito.launch(intentExplicito)
    }

    fun getNombreTablaF(farmacia :BFarmacia): String{
        return ""+farmacia.nombreF
    }
}