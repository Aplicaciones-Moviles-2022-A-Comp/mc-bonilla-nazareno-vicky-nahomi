package com.example.examenvickybonilla

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

class AverMedicamentos : AppCompatActivity() {
    var idItemMedicamentos=0
    var arreglo: ArrayList<BMedicamento> =  ArrayList<BMedicamento> ()
    var idItemFarmaci=0
    var idmed=0
    val contenidoIntentExplicito = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result->
        if(result.resultCode == Activity.RESULT_OK){
            if (result.data != null){

            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aver_medicamentos)

        val idFa=intent.getIntExtra("idFarmacia",0)
        val listView=findViewById<ListView>(R.id.lv_medicamentos)
        arreglo=BBaseDatos.TablaFarmacia!!.mostrarMedicamentos(idFa)
        val adaptador=ArrayAdapter(
            this,
            android.R.layout.simple_expandable_list_item_1,
            arreglo
        )
        listView.adapter=adaptador
       // BBaseDatos.TablaFarmacia= SQLiteHelperFarmacia(this)

        idItemFarmaci=idFa


        val tituloFarmacia=findViewById<TextView>(R.id.textNombreDeFarmacia)
        tituloFarmacia.text=BBaseDatos.TablaFarmacia!!.consultarfarmaciaPorId(idFa).nombreF
        val botonACrearMedicamento=findViewById<Button>(R.id.btn_crear_medicamento)
        botonACrearMedicamento
            .setOnClickListener{
                abrirActividadParametros(ACrearMedicamento::class.java)
            }
        val botonVolver=findViewById<Button>(R.id.btn_volver)
        botonVolver.setOnClickListener {
            irActividad(MainActivity::class.java)
        }

        adaptador.notifyDataSetChanged()

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
                idmed=getIDTablaM(itemMeds as BMedicamento).toInt()
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

    fun abrirDialogo(){
        arreglo= BBaseDatos.TablaFarmacia!!.mostrarMedicamentos(idItemFarmaci)
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar?")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{
                    dialog, which ->
                val listView=findViewById<ListView>(R.id.lv_medicamentos)
                val itemMeds=listView.getItemAtPosition(idItemMedicamentos)
                BBaseDatos.TablaFarmacia!!.eliminarMedicamentoFormulario(getIDTablaM(itemMeds as BMedicamento).toInt())
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

    fun abrirActividadParametros(
        clase:Class<*>,
    ){
        val intentExplicito = Intent(this, clase)

        intentExplicito.putExtra("idMedicament",idmed)
        intentExplicito.putExtra("idFarmacia",idItemFarmaci)
        //intentExplicito.putExtra("idFarmacia",idItemFarmaci)
        contenidoIntentExplicito.launch(intentExplicito)
    }

    fun getIDTablaM(medica :BMedicamento): String{
        return ""+medica.idM
    }

    fun actualizarvista(){
        val listView=findViewById<ListView>(R.id.lv_medicamentos)
        arreglo= BBaseDatos.TablaFarmacia!!.mostrarMedicamentos(idItemFarmaci)
        val adaptador=ArrayAdapter(
            this,
            android.R.layout.simple_expandable_list_item_1,
            arreglo
        )
        listView.adapter=adaptador
        adaptador.notifyDataSetChanged()
    }

}