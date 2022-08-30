package com.example.examen02vb


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BEditarFarmacia : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beditar_farmacia)
        val idFa=intent.getLongExtra("idFarmacia",0)
        val nomFa=intent.getStringExtra("nombreF")
        val nombreFarm=findViewById<EditText>(R.id.editNombreF)
        nombreFarm.setText(nomFa)
        val botonActualizar=findViewById<Button>(R.id.btn_actualizar_f)
        botonActualizar
            .setOnClickListener {
                editFarmacia(nombreFarm.text.toString(), idFa )
                nombreFarm.setText("")
                irActividad(MainActivity::class.java)
            }
    }
    fun editFarmacia(nuevoNombreFarmacia:String, idFa: Long){
        val db= Firebase.firestore
        val so = db.collection("examen02")
        val dataFarmacia = hashMapOf(
            "idFarmacia" to idFa,
            "nombreF" to nuevoNombreFarmacia
        )
        so.document(idFa.toString()).set(dataFarmacia)
    }
    fun irActividad(
        clase: Class<*>
    ){
        val intent= Intent(this,clase)
        startActivity(intent) //definido en la clase AppCompatActivity() heredada
    }
}