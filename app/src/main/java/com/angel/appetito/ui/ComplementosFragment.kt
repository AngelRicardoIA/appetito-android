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

class ComplementosFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_complementos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerProductos)

        val lista = when(nombreRestaurante) {

            "Tortas Ahogadas \"Al estilo Jalisco\"" -> listOf(
                Producto("Extra salsa picante", "$10", R.drawable.rest1),
                Producto("Cebolla curtida", "$8", R.drawable.rest1),
                Producto("Aguacate", "$15", R.drawable.rest1)
            )

            "CherryBlossom - Sushi Place" -> listOf(
                Producto("Soya extra", "$5", R.drawable.rest2),
                Producto("Wasabi", "$10", R.drawable.rest2),
                Producto("Jengibre", "$10", R.drawable.rest2)
            )

            "Tacontento" -> listOf(
                Producto("Salsa extra", "$5", R.drawable.rest3),
                Producto("Cilantro y cebolla", "$5", R.drawable.rest3),
                Producto("Limones", "$5", R.drawable.rest3)
            )

            "DeliCrepas" -> listOf(
                Producto("Extra topping", "$15", R.drawable.rest4),
                Producto("Helado", "$20", R.drawable.rest4),
                Producto("Lechera", "$10", R.drawable.rest4)
            )

            else -> listOf(
                Producto("Aguacate", "$15", R.drawable.rest1)
            )
        }

        recyclerView.adapter = ProductoAdapter(lista)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    companion object {
        fun newInstance(nombre: String) = ComplementosFragment().apply {
            arguments = Bundle().apply {
                putString("nombre", nombre)
            }
        }
    }
}