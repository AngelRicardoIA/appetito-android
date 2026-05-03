package com.angel.appetito.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.angel.appetito.R
import com.angel.appetito.model.Producto

class ProductoAdapter(private val lista: List<Producto>) :
    RecyclerView.Adapter<ProductoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.nombreProducto)
        val precio: TextView = itemView.findViewById(R.id.precioProducto)
        val imagen: ImageView = itemView.findViewById(R.id.imagenProducto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = lista[position]

        holder.nombre.text = producto.nombre
        holder.precio.text = producto.precio
        holder.imagen.setImageResource(producto.imagen)
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}