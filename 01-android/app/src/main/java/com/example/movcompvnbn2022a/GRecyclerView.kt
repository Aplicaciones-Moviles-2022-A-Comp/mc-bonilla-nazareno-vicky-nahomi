package com.example.movcompvnbn2022a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GRecyclerView : AppCompatActivity() {
    var totalLikes=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grecycler_view)
        val listEntrenador= arrayListOf<BEntrenador>()
        listEntrenador.add(BEntrenador(1,"Nahomi","Bonilla"))
        listEntrenador.add(BEntrenador(2,"Vicky","Bon"))
        val reciclerView=findViewById<RecyclerView>(R.id.rv_entrenadores)
        inicializarRV(
            listEntrenador,reciclerView
        )

    }

    fun inicializarRV(
        lista:ArrayList<BEntrenador>,
        reciclerView: RecyclerView
    ){
        val adaptador=FRecypeViewAdaptadorNombreCedula(this,lista,reciclerView)
        reciclerView.adapter=adaptador
        reciclerView.itemAnimator=androidx.recyclerview.widget.DefaultItemAnimator()
        reciclerView.layoutManager=androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }
    fun totalLikes(){
        totalLikes=totalLikes+1
        val totalLikesView=findViewById<TextView>(R.id.tv_total_likes)
        totalLikesView.text=totalLikes.toString()
    }
}
