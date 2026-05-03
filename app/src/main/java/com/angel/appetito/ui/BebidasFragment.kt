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

class BebidasFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_bebidas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerProductos)

        val lista = when(nombreRestaurante) {

            "Tortas Ahogadas \"Al estilo Jalisco\"" -> listOf(
                Producto("Agua de jamaica", "$25", R.drawable.rest1),
                Producto("Refresco", "$22", R.drawable.rest1),
                Producto("Tejuino", "$30", R.drawable.rest1)
            )

            "CherryBlossom - Sushi Place" -> listOf(
                Producto("Té verde", "$30", R.drawable.rest2),
                Producto("Refresco", "$25", R.drawable.rest2),
                Producto("Ramune", "$45", R.drawable.rest2)
            )

            "Tacontento" -> listOf(
                Producto("Horchata", "$25", R.drawable.rest3),
                Producto("Refresco", "$22", R.drawable.rest3),
                Producto("Agua mineral", "$20", R.drawable.rest3)
            )

            "DeliCrepas" -> listOf(
                Producto("Café americano", "$30", R.drawable.rest4),
                Producto("Capuccino", "$45", R.drawable.rest4),
                Producto("Frappé", "$55", R.drawable.rest4)
            )

            else -> listOf(
                Producto("Agua de jamaica", "$25", R.drawable.rest1)
            )
        }

        recyclerView.adapter = ProductoAdapter(lista)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    companion object {
        fun newInstance(nombre: String) = BebidasFragment().apply {
            arguments = Bundle().apply {
                putString("nombre", nombre)
            }
        }
    }
}