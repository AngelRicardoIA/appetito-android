package com.angel.appetito.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.angel.appetito.R
import com.angel.appetito.model.Producto
import com.angel.appetito.ui.DetailActivity

class ProductoAdapter(private val lista: List<Producto>) :
    RecyclerView.Adapter<ProductoAdapter.ViewHolder>() {

    private val listaOriginal = lista.toMutableList()
    private val listaFiltrada = lista.toMutableList()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre = itemView.findViewById<TextView>(R.id.nombreProducto)
        val precio = itemView.findViewById<TextView>(R.id.precioProducto)
        val imagen = itemView.findViewById<ImageView>(R.id.imagenProducto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = listaFiltrada[position]

        holder.nombre.text = producto.nombre
        holder.precio.text = producto.precio
        holder.imagen.setImageResource(producto.imagen)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context

            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("nombre", producto.nombre)
            intent.putExtra("precio", producto.precio)
            intent.putExtra("imagen", producto.imagen)
            intent.putExtra("descripcion", producto.descripcion)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listaFiltrada.size

    fun filtrar(texto: String) {
        listaFiltrada.clear()

        if (texto.isEmpty()) {
            listaFiltrada.addAll(listaOriginal)
        } else {
            val filtro = texto.lowercase()

            listaOriginal.forEach {
                if (it.nombre.lowercase().contains(filtro)) {
                    listaFiltrada.add(it)
                }
            }
        }

        notifyDataSetChanged()
    }
}