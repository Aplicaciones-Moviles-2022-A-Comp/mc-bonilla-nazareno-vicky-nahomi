package com.example.ytmusic

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AdaptadorQPicks (
    private val contexto: MainActivity,
    private val lista: ArrayList<QPicksData>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<AdaptadorQPicks.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val titulolqpickTextView: TextView
        val artistaqpickTextView: TextView
        val imgquickpImageView: ImageView
        init {
            titulolqpickTextView = view.findViewById(R.id.tv_tittle_quickp)
            artistaqpickTextView = view.findViewById(R.id.tv_artist_quickp)
            imgquickpImageView = view.findViewById(R.id.imgv_quiclp)
        }
        fun render(music:QPicksData){
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
                R.layout.item_quick_picks,
                parent,
                false
            )
        return  MyViewHolder(itemView)
    }

    //Setear datos para operacion
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val quickpickActual = this.lista[position]
        holder.titulolqpickTextView.text = quickpickActual.tittle.toString()
        holder.artistaqpickTextView.text = quickpickActual.artist.toString()
        Glide.with(holder.imgquickpImageView.context).load(quickpickActual.image).into(holder.imgquickpImageView)
        holder.render(quickpickActual)
    }

    //Tamano arreglo
    override fun getItemCount(): Int {
        return  this.lista.size
    }

}