package com.example.examenvickybonilla

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class ACrearFarmacia : AppCompatActivity() {

    //var arreglo: ArrayList<BFarmacia> = BBaseDatos.arregloFarmacia
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acrear_farmacia)
        val botonAddFarmacia =findViewById<Button>(R.id.btn_add_farmacia)
        botonAddFarmacia
            .setOnClickListener {
                val nombreFarmacia=findViewById<EditText>(R.id.textNombreFarmacia)
                addFarmacia(nombreFarmacia.text.toString())
                nombreFarmacia.setText("")
                abrirDialogo()
            }
    }
    fun addFarmacia(nombreF: String){
        val db=Firebase.firestore
        val farmacia=db.collection("examen02")
        val idF= Date().time
        val dataFarm = hashMapOf(
            "idFarmacia" to idF,
            "nombreF" to nombreF
        )
        farmacia.document(idF.toString()).set(dataFarm)
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