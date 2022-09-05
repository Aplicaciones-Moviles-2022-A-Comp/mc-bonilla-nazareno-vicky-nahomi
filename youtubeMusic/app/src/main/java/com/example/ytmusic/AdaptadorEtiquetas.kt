package com.example.ytmusic

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class AdaptadorEtiquetas (
    private val contexto: MainActivity,
    private val lista: ArrayList<String>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<AdaptadorEtiquetas.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val etiquetaPrincipalTextView: TextView
        init {
            etiquetaPrincipalTextView = view.findViewById(R.id.tv_etiquetas)
        }
    }

    //Setear el alyout que se va a utilizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_etiquetas,
                parent,
                false
            )
        return  MyViewHolder(itemView)
    }

    //Setear datos para operacion
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val etiquetaActual = this.lista[position]
        holder.etiquetaPrincipalTextView.text = etiquetaActual.toString()
    }

    //Tamano arreglo
    override fun getItemCount(): Int {
        return  this.lista.size
    }

}