package com.example.examenvickybonilla

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    var arreglo: ArrayList<BFarmacia> = ArrayList<BFarmacia> ()
    var idfa: Long=0
    var nomfa=""
    var idItemFarmacia=0

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
        setContentView(R.layout.activity_main)
        val listView=findViewById<ListView>(R.id.lv_farmacias)
        cargarFarmaciasInicial()
        val botonACrearFarmacia=findViewById<Button>(R.id.btn_crear_farmacia)
        botonACrearFarmacia
            .setOnClickListener{
                irActividad(ACrearFarmacia::class.java)
            }
        registerForContextMenu(listView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //lamamos las opciones del menú
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_farmacia,menu)
        //obtener el id del arraylist seleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemFarmacia = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.mi_editar->{
                "${idItemFarmacia}"
                val listView=findViewById<ListView>(R.id.lv_farmacias)
                val itemFarma=listView.getItemAtPosition(idItemFarmacia)
                idfa=getIDTablaF(itemFarma as BFarmacia).toLong()
                nomfa=getNombreTablaF(itemFarma as BFarmacia).toString()
                abrirActividadParametros(BEditarFarmacia::class.java)
                return true
            }
            R.id.mi_eliminar->{
                "${idItemFarmacia}"
                abrirDialogo()
                return true
            }
            R.id.mi_verMeds->{
                "${idItemFarmacia}"
                val listView=findViewById<ListView>(R.id.lv_farmacias)
                val itemFarma=listView.getItemAtPosition(idItemFarmacia)
                idfa=getIDTablaF(itemFarma as BFarmacia).toLong()
                abrirActividadParametros(AverMedicamentos::class.java)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        actualizarvista()
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent= Intent(this,clase)
        startActivity(intent) //definido en la clase AppCompatActivity() heredada
    }

    fun abrirDialogo(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar?")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{
                    dialog, which ->
                val listView=findViewById<ListView>(R.id.lv_farmacias)
                val itemFarma=listView.getItemAtPosition(idItemFarmacia)
                val idfa=getIDTablaF(itemFarma as BFarmacia)
                deleteFarmacia(idfa)
            }
        )
        builder.setNegativeButton(
            "Cancelar",
            null
        )
        val dialogo = builder.create()
        dialogo.show()
    }


    fun abrirActividadParametros(
        clase:Class<*>,
    ){
        val intentExplicito = Intent(this, clase)
        intentExplicito.putExtra("idFarmacia",idfa)
        contenidoIntentExplicito.launch(intentExplicito)
    }
    fun getIDTablaF(farmacia :BFarmacia): String{
        return ""+farmacia.idF
    }
    fun getNombreTablaF(farmacia :BFarmacia): String{
        return ""+farmacia.nombreF
    }

    fun actualizarvista(){
        val db = Firebase.firestore
        val farmaciaRefUnico = db
            .collection("examen02")
        farmaciaRefUnico
            .get()
            .addOnSuccessListener {
                arreglo = ArrayList<BFarmacia>()
                for (farmacia in it){
                    addFarmacia(arreglo,farmacia)
                }
                actualizarListView()
            }
    }

    fun actualizarListView(){
        val listView=findViewById<ListView>(R.id.lv_farmacias)
        val adaptador=ArrayAdapter(
            this,
            android.R.layout.simple_expandable_list_item_1,
            arreglo
        )
        listView.adapter=adaptador
        adaptador.notifyDataSetChanged()
    }

    fun cargarFarmaciasInicial(){
        val db = Firebase.firestore
        val farmaciaRefUnico = db
            .collection("examen02")
        farmaciaRefUnico
            .get()
            .addOnSuccessListener {
                arreglo = ArrayList<BFarmacia>()
                for (farmacia in it){
                        addFarmacia(arreglo,farmacia)
                }
                actualizarvista()
            }
    }
    fun addFarmacia(
        nuevoArreglo: ArrayList<BFarmacia>,
        farmacia: QueryDocumentSnapshot
    ){
        nuevoArreglo.add(
            BFarmacia(
                farmacia.get("idFarmacia") as Long?,
                farmacia.get("nombreF") as String?
            )
        )
    }

    fun deleteFarmacia(
        nombreFarm : String
    ){
        val db = Firebase.firestore
        val farmacia = db.collection("examen02")
        farmacia.document(nombreFarm).delete()
        actualizarvista()
    }


}