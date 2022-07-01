package com.example.examenvickybonilla

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog

class AverMedicamentos : AppCompatActivity() {

    var arreglo: ArrayList<BMedicamento> = BBaseDatos.arregloMedicamentos
    var idItemMedicamentos=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aver_medicamentos)

        val listView=findViewById<ListView>(R.id.lv_farmacias)
        val adaptador=ArrayAdapter(
            this,
            android.R.layout.simple_expandable_list_item_1,
            arreglo
        )
        listView.adapter=adaptador
        adaptador.notifyDataSetChanged()
        val botonACrearFarmacia=findViewById<Button>(R.id.btn_crear_farmacia)
        botonACrearFarmacia
            .setOnClickListener{
                irActividad(ACrearFarmacia::class.java)
            }
        val tituloFarmacia=findViewById<TextView>(R.id.textNombreDeFarmacia)
        tituloFarmacia.text="texto provisional"


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
        inflater.inflate(R.menu.menu_farmacia,menu)
        //obtener el id del arraylist seleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemMedicamentos = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.mi_editarM->{
                "${idItemMedicamentos}"
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
        val listView=findViewById<ListView>(R.id.lv_farmacias)
        val adaptador= ArrayAdapter(
            this,
            android.R.layout.simple_expandable_list_item_1,
            arreglo
        )
        listView.adapter=adaptador
        adaptador.notifyDataSetChanged()
    }

    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar?")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{
                    dialog, which ->
            }
        )
        builder.setNegativeButton(
            "Cancelar",
            null
        )
        val dialogo = builder.create()
        dialogo.show()

    }
}