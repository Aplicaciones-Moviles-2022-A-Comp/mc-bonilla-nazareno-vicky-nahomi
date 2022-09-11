package com.example.calculadoraimc

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class GraficaIMC : AppCompatActivity() {

    var arreglo = ArrayList<IMCData>()
    var correoGlobal:String? = ""

    val contenidoIntentExplicito = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result->
        if(result.resultCode == Activity.RESULT_OK){
            if (result.data != null){
                val data = result.data
                //Log.i("intent-epn","${data?.getStringExtra("nombreModificado")}")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grafica_imc)
        requestedOrientation=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        var correo = intent.getStringExtra("correo")
        correoGlobal=correo
        val linechat = findViewById<LineChart>(R.id.lc_historial)
        cargarDatos(correoGlobal)
        linechat.notifyDataSetChanged()
        val btnImgGrafica = findViewById<ImageButton>(R.id.btnimg_atras_grafica)
        btnImgGrafica.setOnClickListener{
            abrirActividadParametros(HistoriaIMC::class.java)
        }
    }

    fun abrirActividadParametros(
        clase:Class<*>
    ){
        val intentExplicito = Intent(this, clase)
        intentExplicito.putExtra("correo",correoGlobal)
        contenidoIntentExplicito.launch(intentExplicito)
    }

    fun setLineCharData(arreglo:ArrayList<IMCData>){
        var xvalue = ArrayList<String?>()
        var n = arreglo.size-1
        for (i in 0..n){
            xvalue.add(arreglo[i].fecha)
        }

        var yvalue = ArrayList<Entry>()
        for (i in 0..n){
            yvalue.add(Entry(arreglo[i].imc!!.toFloat(),i))
        }

        val linedataset = LineDataSet(yvalue,"IMC")
        linedataset.color = getColor(R.color.purple_500)
        linedataset.setCircleColorHole(getColor(R.color.red))
        val data = LineData(xvalue,linedataset)

        val linechat = findViewById<LineChart>(R.id.lc_historial)
        val xaxis = linechat.xAxis
        linechat.setBackgroundColor(getColor(R.color.bk_grafica))
        xaxis.position = XAxis.XAxisPosition.BOTTOM
        linechat.animateXY(2000,2000)
        linechat.data = data

    }

    fun cargarDatos(
        correo:String?
    ){
        val db = Firebase.firestore
        val imcRefUnico = db
            .collection("usuarios")
            .document("${correo}")
            .collection("historial")

        imcRefUnico
            .get()
            .addOnSuccessListener {
                arreglo = ArrayList<IMCData>()
                for (imcitem in it) {
                    aniadirIMC(arreglo, imcitem)
                }
                setLineCharData(arreglo)
            }

    }

    fun aniadirIMC(
        arregloNuevo: ArrayList<IMCData>,
        imc: QueryDocumentSnapshot
    ) {
        arregloNuevo.add(
            IMCData(
                imc.get("fecha") as String?,
                imc.get("imc") as Double?,
                imc.get("peso") as Double?,
                imc.get("altura") as Double?
            )
        )
    }

    override fun onResume() {
        super.onResume()
        val linechat = findViewById<LineChart>(R.id.lc_historial)
        cargarDatos(correoGlobal)
        linechat.notifyDataSetChanged()
    }
}