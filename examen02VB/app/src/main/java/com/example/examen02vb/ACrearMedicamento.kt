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
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class ACrearMedicamento : AppCompatActivity() {


    val contenidoIntentExplicito = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result->
        if(result.resultCode == Activity.RESULT_OK){
            if (result.data != null){

            }
        }
    }

    var idItemFarmaci : Long=0
    var nombreF=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acrear_medicamento)
        val idFarma=intent.getLongExtra("idFarmacia",0)
        nombreF=intent.getStringExtra("nombreF").toString()
        idItemFarmaci=idFarma
        val nuevaMed=findViewById<EditText>(R.id.textNombreMed)
        val botonCrearMeds=findViewById<Button>(R.id.btn_add_medicamento)
        botonCrearMeds
            .setOnClickListener {
                addMed(nuevaMed.text.toString())
                nuevaMed.setText("")
                abrirDialogo()
            }
    }
    fun addMed(nombreMed:String){
        val db = Firebase.firestore
        val medRefUnico=db.collection("examen02")
            .document("${idItemFarmaci}")
            .collection("Medicamentos")
        val idmeds=Date().time
        val dataMeds= hashMapOf(
            "idMedicamento" to idmeds,
            "nombreM" to nombreMed
        )
        medRefUnico.document(idmeds.toString()).set(dataMeds)
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
            //"volver a  ${ BBaseDatos.TablaFarmacia!!.consultarfarmaciaPorId(idItemFarmaci).nombreF}",
            "regresar",
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
        intentExplicito.putExtra("nombreF",nombreF)
        contenidoIntentExplicito.launch(intentExplicito)
    }


    fun irActividad(
        clase: Class<*>
    ){
        val intent= Intent(this,clase)
        startActivity(intent) //definido en la clase AppCompatActivity() heredada
    }


}