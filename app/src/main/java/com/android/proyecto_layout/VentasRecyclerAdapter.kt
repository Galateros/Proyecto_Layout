package com.android.proyecto_layout

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView


class VentasRecyclerAdapter( private val list: List<Ventas>)
    : RecyclerView.Adapter<VentaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VentaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return VentaViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: VentaViewHolder, position: Int) {
        val item: Ventas = list[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            it.findNavController().navigate(R.id.infoMaterialFragment)
        }
    }

    override fun getItemCount(): Int = list.size

}

class VentaViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.fabrica_layout, parent, false)) {
    private var cantidadItemTextView: TextView? = null
    private var itemDescripcionTextView: TextView? = null



    init {
        cantidadItemTextView = itemView.findViewById(R.id.item_nombre)
        itemDescripcionTextView = itemView.findViewById(R.id.item_descipcion)

    }

    fun bind(item: Ventas) {
        Log.i("Error despensa",item.nombre.toString() )
        cantidadItemTextView?.text = item.nombre
        itemDescripcionTextView?.text = item.descripcion


    }

}