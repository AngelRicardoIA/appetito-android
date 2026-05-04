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
import com.angel.appetito.ui.MenuActivity
import com.angel.appetito.R
import com.angel.appetito.database.DatabaseHelper
import com.angel.appetito.model.Restaurante
import com.angel.appetito.ui.AddRestaurantActivity
import kotlin.jvm.java


class RestauranteAdapter(lista: List<Restaurante>) : RecyclerView.Adapter<RestauranteAdapter.ViewHolder>() {

    private var lista = lista.toMutableList()
    private var listaOriginal = lista.toMutableList()
    private var listaFiltrada = lista.toMutableList()

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
        val restaurante = listaFiltrada[p1]
        p0.nombre.text = restaurante.nombre
        p0.direccion.text = restaurante.direccion
        p0.imagen.setImageResource(restaurante.imagen)


        p0.itemView.setOnClickListener {
            val context = p0.itemView.context

            val intent = Intent(context, MenuActivity::class.java)
            intent.putExtra("id", restaurante.id)

            context.startActivity(intent)
        }
        p0.itemView.setOnLongClickListener {

            val context = p0.itemView.context

            val opciones = arrayOf("Editar", "Eliminar")

            androidx.appcompat.app.AlertDialog.Builder(context)
                .setTitle("Opciones")
                .setItems(opciones) { _, which ->

                    when (which) {

                        0 -> {
                            val intent = Intent(context, AddRestaurantActivity::class.java)
                            intent.putExtra("modo", "editar")
                            intent.putExtra("id", restaurante.id)
                            intent.putExtra("nombre", restaurante.nombre)
                            intent.putExtra("direccion", restaurante.direccion)
                            intent.putExtra("imagen", restaurante.imagen)

                            context.startActivity(intent)
                        }

                        1 -> {
                            com.google.android.material.dialog.MaterialAlertDialogBuilder(context)
                                .setTitle("Eliminar restaurante")
                                .setMessage("¿Seguro que quieres eliminar este restaurante?")
                                .setPositiveButton("Eliminar") { _, _ ->

                                    val dbHelper = DatabaseHelper(context)
                                    dbHelper.deleteRestaurant(restaurante.id)

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

    override fun getItemCount(): Int {
        return listaFiltrada.size
    }

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

    fun actualizarLista(nuevaLista: List<Restaurante>) {
        lista.clear()
        lista.addAll(nuevaLista)

        listaOriginal.clear()
        listaOriginal.addAll(nuevaLista)

        listaFiltrada.clear()
        listaFiltrada.addAll(nuevaLista)

        notifyDataSetChanged()
    }
}