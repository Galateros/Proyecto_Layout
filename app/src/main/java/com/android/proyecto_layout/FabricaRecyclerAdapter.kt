package com.android.proyecto_layout

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView


class FabricaRecyclerAdapter( private val list: List<Fabrica>)
    : RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item: Fabrica = list[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {

            

            it.findNavController().navigate(R.id.infoFactoryFragment)

        }

    }

    override fun getItemCount(): Int = list.size

}

class ItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.fabrica_layout, parent, false)) {
    private var cantidadItemTextView: TextView? = null
    private var itemDescripcionTextView: TextView? = null



    init {
        cantidadItemTextView = itemView.findViewById(R.id.item_nombre)
        itemDescripcionTextView = itemView.findViewById(R.id.item_descipcion)

    }

    fun bind(item: Fabrica) {
        Log.i("Error despensa",item.nombre.toString() )
        cantidadItemTextView?.text = item.nombre
        itemDescripcionTextView?.text = item.descripcion


    }

}