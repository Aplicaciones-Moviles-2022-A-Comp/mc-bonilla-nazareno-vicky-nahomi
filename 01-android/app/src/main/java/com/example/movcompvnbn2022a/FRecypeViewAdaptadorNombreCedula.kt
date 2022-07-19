package com.example.movcompvnbn2022a

//import android.view.Viewimport android.view.LayoutInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class FRecypeViewAdaptadorNombreCedula(

    private val contexto: GRecyclerView,
    private val lista: ArrayList<BEntrenador>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<FRecypeViewAdaptadorNombreCedula.MyViewHolder>(){

    inner class MyViewHolder(
        view: View,
    ) : RecyclerView.ViewHolder(view){
        val nombretv:TextView
        val cedulatv:TextView
        val likestv:TextView
        val btnLike:Button
        var numLikes=0
        init {
            nombretv=view.findViewById(R.id.tv_nombre)
            cedulatv=view.findViewById(R.id.tv_cedula)
            likestv=view.findViewById(R.id.tv_likes)
            btnLike=view.findViewById(R.id.btn_dar_like)
            btnLike.setOnClickListener {
                like()
            }
        }
        fun like(){
            numLikes=numLikes+1
            likestv.text=numLikes.toString()
            contexto.totalLikes()
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_view_vista, parent,false
        )//se pasan los parámetros
        //se setea lo que se va a usar del layout
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(
        //setear datos
        holder:MyViewHolder,
        position: Int
    ) {
        val entrenadorActual=this.lista[position]
        holder.nombretv.text=entrenadorActual.nombre
        holder.cedulatv.text=entrenadorActual.descripcion
        holder.btnLike.text="like ${entrenadorActual.nombre}"
        holder.likestv.text="0"
    }

    override fun getItemCount(): Int {
        return  this.lista.size
    }

}