package com.angel.appetito.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.angel.appetito.R
import com.angel.appetito.adapter.ProductoAdapter
import com.angel.appetito.model.Producto

class ComidaFragment : Fragment(R.layout.fragment_comida) {

    private var nombreRestaurante: String? = null
    private lateinit var adapter: ProductoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nombreRestaurante = arguments?.getString("nombre")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerProductos)

        val lista = when (nombreRestaurante) {

            "Tortas Ahogadas \"Al estilo Jalisco\"" -> listOf(
                Producto("Torta ahogada clásica", "$65", R.drawable.rest1, "Birote con carnitas en salsa."),
                Producto("Torta de Camaron", "$75", R.drawable.torta_camaron, "Birote con camarón."),
                Producto("Tacos dorados ahogados", "$55", R.drawable.tacos_ahogados, "Tacos fritos con salsa.")
            )

            "CherryBlossom - Sushi Place" -> listOf(
                Producto("Sushi roll California", "$120", R.drawable.california, "Sushi clásico."),
                Producto("Sushi empanizado", "$140", R.drawable.empanizado, "Sushi crujiente."),
                Producto("Yakimeshi mixto", "$110", R.drawable.yakimeshi, "Arroz japonés.")
            )

            "Tacontento" -> listOf(
                Producto("Tacos al pastor (3)", "$45", R.drawable.pastor, "Tacos con piña."),
                Producto("Tacos de asada (3)", "$55", R.drawable.asada, "Carne asada."),
                Producto("Gringa", "$60", R.drawable.gringa, "Tortilla con queso.")
            )

            "DeliCrepas" -> listOf(
                Producto("Crepa Nutella", "$70", R.drawable.nutella, "Dulce."),
                Producto("Crepa jamón y queso", "$65", R.drawable.jamon, "Salada."),
                Producto("Crepa fresa con crema", "$75", R.drawable.fresa, "Fresas.")
            )

            else -> listOf(
                Producto("Torta ahogada", "$65", R.drawable.rest1, "default")
            )
        }

        adapter = ProductoAdapter(lista)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    fun filtrar(texto: String) {
        adapter.filtrar(texto)
    }

    companion object {
        fun newInstance(nombre: String) = ComidaFragment().apply {
            arguments = Bundle().apply {
                putString("nombre", nombre)
            }
        }
    }
}