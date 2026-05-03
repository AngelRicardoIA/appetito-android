package com.angel.appetito.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.angel.appetito.ui.MenuActivity
import com.angel.appetito.R
import com.angel.appetito.model.Restaurante
import kotlin.jvm.java


class RestauranteAdapter(lista: List<Restaurante>) : RecyclerView.Adapter<RestauranteAdapter.ViewHolder>() {

    val lista = lista

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre = itemView.findViewById<TextView>(R.id.nombre)
        val direccion = itemView.findViewById<TextView>(R.id.direccion)
        val imagen = itemView.findViewById<ImageView>(R.id.imagen)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_restaurante,
            p0, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val restaurante = lista[p1]
        p0.nombre.text = restaurante.nombre
        p0.direccion.text = restaurante.direccion
        p0.imagen.setImageResource(restaurante.imagen)

        p0.itemView.setOnClickListener {
            val context = p0.itemView.context

            val intent = Intent(context, MenuActivity::class.java)
            intent.putExtra("nombre", restaurante.nombre)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}