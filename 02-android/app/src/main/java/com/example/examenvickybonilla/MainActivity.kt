package com.example.examenvickybonilla

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
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    var arreglo: ArrayList<BFarmacia> = BBaseDatos.arregloFarmacia
    var idItemFarmacia=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView=findViewById<ListView>(R.id.lv_farmacias)
        val adaptador=ArrayAdapter(
            this,
            android.R.layout.simple_expandable_list_item_1,
            arreglo
        )
        listView.adapter=adaptador

        val botonACrearFarmacia=findViewById<Button>(R.id.btn_crear_farmacia)
        botonACrearFarmacia
            .setOnClickListener{
                irActividad(ACrearFarmacia::class.java)
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
                return true
            }
            R.id.mi_eliminar->{
                "${idItemFarmacia}"
                abrirDialogo()
                return true
            }
            R.id.mi_verMeds->{
                "${idItemFarmacia}"
                irActividad(AverMedicamentos::class.java)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        val listView=findViewById<ListView>(R.id.lv_farmacias)
        val adaptador=ArrayAdapter(
            this,
            android.R.layout.simple_expandable_list_item_1,
            arreglo
        )
        listView.adapter=adaptador
        adaptador.notifyDataSetChanged()
    }

   /* override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val listView=findViewById<ListView>(R.id.lv_farmacias)
        val adaptador=ArrayAdapter(
            this,
            android.R.layout.simple_expandable_list_item_1,
            arreglo
        )
        listView.adapter=adaptador
        adaptador.notifyDataSetChanged()
    }

    override fun onStop() {
        super.onStop()
        val listView=findViewById<ListView>(R.id.lv_farmacias)
        val adaptador=ArrayAdapter(
            this,
            android.R.layout.simple_expandable_list_item_1,
            arreglo
        )
        listView.adapter=adaptador
        adaptador.notifyDataSetChanged()
    }

    override fun onStart() {
        super.onStart()
        val listView=findViewById<ListView>(R.id.lv_farmacias)
        val adaptador=ArrayAdapter(
            this,
            android.R.layout.simple_expandable_list_item_1,
            arreglo
        )
        listView.adapter=adaptador
        adaptador.notifyDataSetChanged()
    }*/

    fun abrirDialogo(){
        /*val listView=findViewById<ListView>(R.id.lv_farmacias)
        val adaptador=ArrayAdapter(
            this,
            android.R.layout.simple_expandable_list_item_1,
            arreglo
        )*/

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar?")
        builder.setPositiveButton(
            "Aceptar ${idItemFarmacia}",
            DialogInterface.OnClickListener{
                    dialog, which ->
                    arreglo.removeAt(0)
            }
        )
        builder.setNegativeButton(
            "Cancelar",
            null
        )
        val dialogo = builder.create()
        dialogo.show()
       // listView.adapter=adaptador
       // adaptador.notifyDataSetChanged()

    }

}