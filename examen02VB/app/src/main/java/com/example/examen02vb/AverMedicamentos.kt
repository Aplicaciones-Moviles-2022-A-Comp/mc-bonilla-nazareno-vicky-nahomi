package com.example.examen02vb

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AverMedicamentos : AppCompatActivity() {
    var idItemMedicamentos=0
    var arreglo: ArrayList<BMedicamento> =  ArrayList<BMedicamento> ()
    var idFa: Long=0
    var idmed:Long=0
    var nombreM=""
    var nombreF=""
    val contenidoIntentExplicito = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result->
        if(result.resultCode == Activity.RESULT_OK){
            if (result.data != null){
                val data = result.data
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aver_medicamentos)

        idFa=intent.getLongExtra("idFarmacia",0)
        nombreF=intent.getStringExtra("nombreF").toString()
        //cargarMedicinasInicial()
        actualizarvista()
        val tituloFarmacia=findViewById<TextView>(R.id.textNombreDeFarmacia)
        tituloFarmacia.setText(nombreF)
        val listView=findViewById<ListView>(R.id.lv_medicamentos)
        val botonACrearMedicamento=findViewById<Button>(R.id.btn_crear_medicamento)
        botonACrearMedicamento
            .setOnClickListener{
                abrirActividadParametros(ACrearMedicamento::class.java)
            }
        val botonVolver=findViewById<Button>(R.id.btn_volver)
        botonVolver.setOnClickListener {
            irActividad(MainActivity::class.java)
        }
        registerForContextMenu(listView)

    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent= Intent(this,clase)
        startActivity(intent) //definido en la clase AppCompatActivity() heredada
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //lamamos las opciones del menú
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_meds,menu)
        //obtener el id del arraylist seleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemMedicamentos = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.mi_editarM->{
                "${idItemMedicamentos}"
                val listView=findViewById<ListView>(R.id.lv_medicamentos)
                val itemMeds=listView.getItemAtPosition(idItemMedicamentos)
                idmed=getIDTablaM(itemMeds as BMedicamento).toLong()
                nombreM=getNombreTablaM(itemMeds as BMedicamento)
                abrirActividadParametros(BEditarMedicamento::class.java)
                return true
            }
            R.id.mi_eliminarM->{
                abrirDialogo()
                "${idItemMedicamentos}"
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        actualizarvista()
    }

    fun cargarMedicinasInicial(){
        val db = Firebase.firestore
        val medRefUnico=db.collection("examen02")
            .document("${idFa}")
            .collection("Medicamentos")
        medRefUnico
            .get()
            .addOnSuccessListener {
                arreglo=ArrayList<BMedicamento>()
                for (medicamento in it){
                    addMedicamento(arreglo, medicamento)
                }
                actualizarvista()
            }
    }
    fun abrirDialogo(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar?")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{
                    dialog, which ->
                val listView=findViewById<ListView>(R.id.lv_medicamentos)
                val itemMeds=listView.getItemAtPosition(idItemMedicamentos)
                val idMedSelect= getIDTablaM(itemMeds as BMedicamento)
                deleteMedicamento(idMedSelect)
                actualizarvista()
            }
        )
        builder.setNegativeButton(
            "Cancelar",
            null
        )
        val dialogo = builder.create()
        dialogo.show()
    }
    fun addMedicamento(
        arreglo: ArrayList<BMedicamento>,
        medicamento: QueryDocumentSnapshot){
        arreglo.add(
            BMedicamento(
                medicamento.get("idMedicamento") as Long?,
                medicamento.get("nombreM") as String?
            )
        )
    }
    fun deleteMedicamento(nombreM: String){
        val db = Firebase.firestore
        val medRefUnico=db.collection("examen02")
            .document("${idFa}")
            .collection("Medicamentos")
        medRefUnico.document(nombreM).delete()
        actualizarvista()
    }

    fun abrirActividadParametros(
        clase:Class<*>,
    ){
        val intentExplicito = Intent(this, clase)

        intentExplicito.putExtra("idMedicamento",idmed)
        intentExplicito.putExtra("idFarmacia",idFa)
        intentExplicito.putExtra("nombreF",nombreF)
        intentExplicito.putExtra("nombreM",nombreM)
        contenidoIntentExplicito.launch(intentExplicito)
    }

    fun getIDTablaM(medica :BMedicamento): String{
        return ""+medica.idM
    }
    fun getNombreTablaM(medica :BMedicamento): String{
        return ""+medica.nombreM
    }


    fun actualizarvista(){
        val db = Firebase.firestore
        val medRefUnico=db.collection("examen02")
            .document("${idFa}")
            .collection("Medicamentos")
        medRefUnico
            .get()
            .addOnSuccessListener {
                arreglo=ArrayList<BMedicamento>()
                for (medicamento in it){
                    addMedicamento(arreglo, medicamento)
                }
                actualizarListView()
            }
    }
    fun actualizarListView(){
        val listView=findViewById<ListView>(R.id.lv_medicamentos)
        val adaptador=ArrayAdapter(
            this,
            android.R.layout.simple_expandable_list_item_1,
            arreglo
        )
        listView.adapter=adaptador
        adaptador.notifyDataSetChanged()
    }

}