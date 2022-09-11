package com.example.calculadoraimc

import android.content.Intent
import android.view.*
import android.widget.AdapterView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AdaptadorHistorial (
    private val contexto: HistoriaIMC,
    private val lista: ArrayList<IMCData>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<AdaptadorHistorial.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val fechaTextView: TextView
        val imcTextView: TextView
        //val alturaTextView: TextView
        val pesoTextView: TextView
        init {
            fechaTextView = view.findViewById(R.id.tv_fecha_h)
            imcTextView = view.findViewById(R.id.tv_imc_h)
            //alturaTextView = view.findViewById(R.id.tv_altura_h)
            pesoTextView = view.findViewById(R.id.tv_peso_h)
            itemView.setOnClickListener {
                popupMenus(it)
            }
        }
        fun popupMenus(v:View){
            val popupMenus = PopupMenu(
                contexto.applicationContext,
                v
            )
            popupMenus.inflate(R.menu.menueditar)
            popupMenus.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.menu_editar->{
                        irActividad(EditarRegistro::class.java,
                            fechaTextView.text.toString(),
                            contexto.correoGlobal
                            )
                        true
                    }
                    R.id.menu_eliminar->{
                        eliminarIMC(contexto.correoGlobal,fechaTextView.text.toString())
                        contexto.cargarHistorial(contexto.rvHistorial,contexto.correoGlobal)
                        true
                    }
                    else->false
                }
            }
            popupMenus.show()
        }

        fun eliminarIMC(correo:String?,fecha:String){
            val db = Firebase.firestore
            val imc = db.collection("usuarios")
            imc.document("${correo}").collection("historial")
                .document(fecha).delete()
        }

        fun irActividad(
            clase:Class<*>,
            fecha:String?,
            correo:String?
        ){
            var contexto = itemView.context
            val intent = Intent(contexto, clase)
            intent.putExtra("fecha",fecha)
            intent.putExtra("correo",correo)
            contexto.startActivity(intent)
        }
    }

    //Setear el alyout que se va a utilizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_historial,
                parent,
                false
            )
        return  MyViewHolder(itemView)
    }

    //Setear datos para operacion
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val imcActual = this.lista[position]
        holder.fechaTextView.text = imcActual.fecha
        holder.imcTextView.text = imcActual.imc.toString()
        holder.pesoTextView.text = imcActual.peso.toString()
        //holder.alturaTextView.text = imcActual.altura.toString()
    }

    //Tamano arreglo
    override fun getItemCount(): Int {
        return  this.lista.size
    }

}