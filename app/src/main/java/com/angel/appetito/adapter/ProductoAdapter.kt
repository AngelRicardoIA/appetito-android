package com.angel.appetito.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.angel.appetito.R
import com.angel.appetito.database.DatabaseHelper
import com.angel.appetito.model.Producto
import com.angel.appetito.ui.AddFoodActivity
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
        if (producto.imagen != 0) {
            holder.imagen.setImageResource(producto.imagen)
        } else {
            holder.imagen.setImageDrawable(null)
            holder.imagen.setBackgroundResource(R.drawable.placeholder_image)
        }

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context

            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("nombre", producto.nombre)
            intent.putExtra("precio", producto.precio)
            intent.putExtra("imagen", producto.imagen)
            intent.putExtra("descripcion", producto.descripcion)

            context.startActivity(intent)
        }

        holder.itemView.setOnLongClickListener {

            val context = holder.itemView.context
            val opciones = arrayOf("Editar", "Eliminar")

            androidx.appcompat.app.AlertDialog.Builder(context)
                .setTitle("Opciones")
                .setItems(opciones) { _, which ->

                    when (which) {

                        0 -> {
                            val intent = Intent(context, AddFoodActivity::class.java)
                            intent.putExtra("modo", "editar")
                            intent.putExtra("id", producto.id)
                            intent.putExtra("nombre", producto.nombre)
                            intent.putExtra("precio", producto.precio)
                            intent.putExtra("descripcion", producto.descripcion)
                            intent.putExtra("imagen", producto.imagen)
                            intent.putExtra("tipo", "food")
                            context.startActivity(intent)
                        }

                        1 -> {
                            com.google.android.material.dialog.MaterialAlertDialogBuilder(context)
                                .setTitle("Eliminar alimento")
                                .setMessage("¿Seguro que quieres eliminar este alimento?")
                                .setPositiveButton("Eliminar") { _, _ ->

                                    val dbHelper = DatabaseHelper(context)
                                    dbHelper.deleteFood(producto.id)

                                    Toast.makeText(context, "Eliminado", Toast.LENGTH_SHORT).show()

                                    (context as AppCompatActivity).recreate()
                                }
                                .setNegativeButton("Cancelar", null)
                                .show()
                        }
                    }
                }
                .show()

            true
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