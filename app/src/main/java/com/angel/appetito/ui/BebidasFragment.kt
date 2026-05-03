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

    lateinit var adapter: ProductoAdapter
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
                Producto("Agua de jamaica", "$25", R.drawable.jamaica,
                    "Bebida preparada a base de flor de jamaica, ligeramente ácida y servida fría en vaso de 500 ml."),
                Producto("Refresco", "$22", R.drawable.coca,
                    "Bebida carbonatada de 500 ml, disponible en marcas como Coca-Cola, Fanta o Sprite según disponibilidad."),
                Producto("Tejuino", "$30", R.drawable.tejuino,
                    "Bebida tradicional de maíz fermentado, con limón, sal y hielo.")
            )

            "CherryBlossom - Sushi Place" -> listOf(
                Producto("Té verde", "$30", R.drawable.teverde,
                    "Infusión caliente de hojas de té verde, sin azúcar añadida."),
                Producto("Refresco", "$25", R.drawable.coca,
                    "Bebida carbonatada de 500 ml, disponible en marcas comerciales como Coca-Cola o Fanta."),
                Producto("Ramune", "$45", R.drawable.ramune,
                    "Refresco japonés en botella de vidrio con gas, sabor original.")
            )

            "Tacontento" -> listOf(
                Producto("Horchata", "$25", R.drawable.horchata,
                    "Bebida dulce a base de arroz, canela y leche, servida fría en vaso de 500 ml."),
                Producto("Refresco", "$22", R.drawable.coca,
                    "Bebida carbonatada de 500 ml, marcas comerciales disponibles."),
                Producto("Agua mineral", "$20", R.drawable.mineral,
                    "Agua con gas en presentación de 500 ml.")
            )

            "DeliCrepas" -> listOf(
                Producto("Café americano", "$30", R.drawable.americano,
                    "Café negro preparado con grano molido, servido caliente en taza."),
                Producto("Capuccino", "$45", R.drawable.cappuccino,
                    "Bebida caliente a base de café espresso con leche espumada."),
                Producto("Frappé", "$55", R.drawable.frappe,
                    "Bebida fría licuada con hielo, leche y sabor a elegir.")
            )

            else -> listOf(
                Producto("Agua de jamaica", "$25", R.drawable.rest1, "default")
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
        fun newInstance(nombre: String) = BebidasFragment().apply {
            arguments = Bundle().apply {
                putString("nombre", nombre)
            }
        }
    }
}