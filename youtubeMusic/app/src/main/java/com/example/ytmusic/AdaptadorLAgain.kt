package com.example.ytmusic

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AdaptadorLAgain (
    private val contexto: MainActivity,
    private val lista: ArrayList<LAgainData>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<AdaptadorLAgain.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val titulolagainTextView: TextView
        val imglagainImageView: ImageView
        init {
            titulolagainTextView = view.findViewById(R.id.tv_tittle_lagain)
            imglagainImageView = view.findViewById(R.id.imgv_lagain)
        }
        fun render(music:LAgainData){
            itemView.setOnClickListener {
                irActividad(MostrarContenido::class.java,music.url)
            }
        }
        fun irActividad(
            clase:Class<*>,
            url:String
        ){
            var contexto = itemView.context
            val intent = Intent(contexto, clase)
            intent.putExtra("url",url)
            contexto.startActivity(intent)
        }
    }

    //Setear el alyout que se va a utilizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_listen_again,
                parent,
                false
            )
        return  MyViewHolder(itemView)
    }

    //Setear datos para operacion
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val lagainActual = this.lista[position]
        holder.titulolagainTextView.text = lagainActual.tittle.toString()
        Glide.with(holder.imglagainImageView.context).load(lagainActual.image).into(holder.imglagainImageView)
        holder.render(lagainActual)
    }

    //Tamano arreglo
    override fun getItemCount(): Int {
        return  this.lista.size
    }

}