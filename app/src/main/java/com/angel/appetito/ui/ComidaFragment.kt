package com.angel.appetito.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.angel.appetito.R
import com.angel.appetito.adapter.ProductoAdapter
import com.angel.appetito.model.Producto

class ComidaFragment : Fragment() {

    private var nombreRestaurante: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nombreRestaurante = arguments?.getString("nombre")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_comida, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerProductos)

        val lista = when (nombreRestaurante) {
            "Tortas Ahogadas \"Al estilo Jalisco\"" -> listOf(
                Producto("Torta ahogada clásica", "$65", R.drawable.rest1),
                Producto("Torta de carnitas", "$75", R.drawable.rest1),
                Producto("Tacos dorados ahogados", "$55", R.drawable.rest1)
            )

            "CherryBlossom - Sushi Place" -> listOf(
                Producto("Sushi roll California", "$120", R.drawable.rest2),
                Producto("Sushi empanizado", "$140", R.drawable.rest2),
                Producto("Yakimeshi mixto", "$110", R.drawable.rest2)
            )

            "Tacontento" -> listOf(
                Producto("Tacos al pastor (3)", "$45", R.drawable.rest3),
                Producto("Tacos de asada (3)", "$55", R.drawable.rest3),
                Producto("Gringa", "$60", R.drawable.rest3)
            )

            "DeliCrepas" -> listOf(
                Producto("Crepa Nutella", "$70", R.drawable.rest4),
                Producto("Crepa jamón y queso", "$65", R.drawable.rest4),
                Producto("Crepa fresa con crema", "$75", R.drawable.rest4)
            )

            else -> listOf(
                Producto("Torta ahogada", "$65", R.drawable.rest1)
            )
        }

        recyclerView.adapter = ProductoAdapter(lista)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    companion object {
        fun newInstance(nombre: String) = ComidaFragment().apply {
            arguments = Bundle().apply {
                putString("nombre", nombre)
            }
        }
    }
}