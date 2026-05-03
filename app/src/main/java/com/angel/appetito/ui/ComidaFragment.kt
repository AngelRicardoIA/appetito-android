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
                Producto("Torta ahogada clásica", "$65", R.drawable.rest1,
                    "Birote salado relleno de carnitas de cerdo, bañado en salsa de jitomate y chile."),
                Producto("Torta de Camaron", "$75", R.drawable.torta_camaron,
                    "Birote relleno de camarón en salsa, acompañado de cebolla y limón."),
                Producto("Tacos dorados ahogados", "$55", R.drawable.tacos_ahogados,
                    "Tacos fritos rellenos, bañados en salsa y acompañados de crema y queso.")
            )

            "CherryBlossom - Sushi Place" -> listOf(
                Producto("Sushi roll California", "$120", R.drawable.california,
                    "Rollo de sushi con arroz, alga, pepino, aguacate y surimi."),
                Producto("Sushi empanizado", "$140", R.drawable.empanizado,
                    "Rollo de sushi cubierto con empanizado crujiente, relleno de proteína y vegetales."),
                Producto("Yakimeshi mixto", "$110", R.drawable.yakimeshi,
                    "Arroz frito estilo japonés con vegetales, huevo y proteína mixta.")
            )

            "Tacontento" -> listOf(
                Producto("Tacos al pastor (3)", "$45", R.drawable.pastor,
                    "Tortillas de maíz con carne al pastor, piña, cebolla y cilantro."),
                Producto("Tacos de asada (3)", "$55", R.drawable.asada,
                    "Tortillas con carne asada, servidas con cebolla, cilantro y limón."),
                Producto("Gringa", "$60", R.drawable.gringa,
                    "Tortilla de harina con carne al pastor, queso fundido y salsa.")
            )

            "DeliCrepas" -> listOf(
                Producto("Crepa Nutella", "$70", R.drawable.nutella,
                    "Crepa dulce rellena de crema de avellana."),
                Producto("Crepa jamón y queso", "$65", R.drawable.jamon,
                    "Crepa salada rellena de jamón y queso fundido."),
                Producto("Crepa fresa con crema", "$75", R.drawable.fresa,
                    "Crepa dulce con fresas frescas y crema.")
            )

            else -> listOf(
                Producto("Torta ahogada", "$65", R.drawable.rest1, "default")
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