package com.example.movcompvnbn2022a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class IFirebaseFirestore : AppCompatActivity() {
    var arreglo: ArrayList<IcitiesDto> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ifirebase_firestore)
        val listView =findViewById<ListView>(R.id.lv_list_view)
        val adaptador= ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )
        listView.adapter=adaptador
        adaptador.notifyDataSetChanged()

        val botonDatosPrueba=findViewById<Button>(R.id.btn_fs_datos_prueba)
        botonDatosPrueba.setOnClickListener { crearDatosPrueba() }
        val  db = Firebase.firestore
        val citiesREfUnico=db
            .collection("cities")
        val botonOrderBy=findViewById<Button>(R.id.btn_fs_order_by)
        botonOrderBy.setOnClickListener {
            limpiarArreglo()
            adaptador.notifyDataSetChanged()
            citiesREfUnico
                .orderBy("population")
                .get()
                .addOnSuccessListener {
                    arreglo= arrayListOf()
                    for (ciudad in it){
                        anadirArregloCiudad(arreglo,ciudad,adaptador)
                    }
                    adaptador.notifyDataSetChanged()
                }
        }
        val botonObtenerDocumento=findViewById<Button>(R.id.btn_fs_odoc)
        botonObtenerDocumento.setOnClickListener {
            limpiarArreglo()
            adaptador.notifyDataSetChanged()
            citiesREfUnico
                .document("BJ")
                .get()
                .addOnSuccessListener {
                    arreglo= arrayListOf()
                    arreglo.add(
                        IcitiesDto(
                            it.get("name") as String?,
                            it.get("state") as String?,
                            it.get("country") as String?,
                            it.get("capital") as Boolean?,
                            it.get("population") as Long?,
                            it.get("regions") as ArrayList<String>
                        )
                    )
                    adaptador.notifyDataSetChanged()
                }
        }
    }

    fun limpiarArreglo(){
        arreglo.clear()
    }
    fun anadirArregloCiudad(
        arregloNuevo:ArrayList<IcitiesDto>,
        ciudad: QueryDocumentSnapshot,
        adaptador: ArrayAdapter<IcitiesDto>
    ){
        val nuevaCiudad =
            IcitiesDto(
                ciudad.get("name") as String?,
                ciudad.get("state") as String?,
                ciudad.get("country") as String?,
                ciudad.get("capital") as Boolean?,
                ciudad.get("population") as Long?,
                ciudad.get("regions") as ArrayList<String>?
            )
        arregloNuevo.add(
            nuevaCiudad
        )
        adaptador.notifyDataSetChanged()
    }
    fun crearDatosPrueba(){
        val db=Firebase.firestore
        val cities = db.collection("cities")

        val data1 = hashMapOf(
            "name" to "San Francisco",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 860000,
            "regions" to listOf("west_coast", "norcal")
        )
        cities.document("SF").set(data1)

        val data2 = hashMapOf(
            "name" to "Los Angeles",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 3900000,
            "regions" to listOf("west_coast", "socal")
        )
        cities.document("LA").set(data2)

        val data3 = hashMapOf(
            "name" to "Washington D.C.",
            "state" to null,
            "country" to "USA",
            "capital" to true,
            "population" to 680000,
            "regions" to listOf("east_coast")
        )
        cities.document("DC").set(data3)

        val data4 = hashMapOf(
            "name" to "Tokyo",
            "state" to null,
            "country" to "Japan",
            "capital" to true,
            "population" to 9000000,
            "regions" to listOf("kanto", "honshu")
        )
        cities.document("TOK").set(data4)

        val data5 = hashMapOf(
            "name" to "Beijing",
            "state" to null,
            "country" to "China",
            "capital" to true,
            "population" to 21500000,
            "regions" to listOf("jingjinji", "hebei")
        )
        cities.document("BJ").set(data5)
    }
}



