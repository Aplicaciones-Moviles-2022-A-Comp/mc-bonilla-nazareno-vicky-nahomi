package com.example.ytmusic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.HorizontalScrollView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rvEtiquetas = findViewById<RecyclerView>(R.id.rv_etiquetas)
        inicializarRVEtiquetas(ProveedorContenido.etiquetas,rvEtiquetas)
        var rvLAgain = findViewById<RecyclerView>(R.id.rv_music)
        inicializarRVListenAgain(ProveedorContenido.lagainmusic,rvLAgain)
        val rvQPicks = findViewById<RecyclerView>(R.id.rv_quick_picks)
        inicializarRVQuickPick(ProveedorContenido.qpicksmusic,rvQPicks)
    }

    fun inicializarRVEtiquetas(
        lista:ArrayList<String>,
        recyclerView: RecyclerView
    ){
        val adaptador = AdaptadorEtiquetas(
            this,
            lista,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        adaptador.notifyDataSetChanged()
    }

    fun inicializarRVListenAgain(
        lista:ArrayList<LAgainData>,
        recyclerView: RecyclerView
    ){
        val adaptador = AdaptadorLAgain(
            this,
            lista,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.GridLayoutManager(this,2,GridLayoutManager.HORIZONTAL,false)
        adaptador.notifyDataSetChanged()
    }

    fun inicializarRVQuickPick(
        lista:ArrayList<QPicksData>,
        recyclerView: RecyclerView
    ){
        val adaptador = AdaptadorQPicks(
            this,
            lista,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.GridLayoutManager(this,4,GridLayoutManager.HORIZONTAL,false)
        adaptador.notifyDataSetChanged()
    }

}