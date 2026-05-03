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
        return inflater.inflate(R.layout.fragment_complementos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerProductos)

        val lista = when(nombreRestaurante) {

            "Tortas Ahogadas \"Al estilo Jalisco\"" -> listOf(
                Producto("Extra salsa", "$10", R.drawable.salsa,
                    "Porción adicional de salsa picante."),
                Producto("Cebolla curtida", "$8", R.drawable.cebolla,
                    "Cebolla preparada con limón y especias."),
                Producto("Aguacate", "$15", R.drawable.aguacate,
                    "Porción de aguacate fresco en rebanadas.")
            )

            "CherryBlossom - Sushi Place" -> listOf(
                Producto("Soya extra", "$5", R.drawable.soja,
                    "Porción adicional de salsa de soya."),
                Producto("Wasabi", "$10", R.drawable.wasabi,
                    "Pasta picante tradicional japonesa."),
                Producto("Jengibre", "$10", R.drawable.jengibre,
                    "Jengibre encurtido para acompañar sushi.")
            )

            "Tacontento" -> listOf(
                Producto("Salsa extra", "$5", R.drawable.salsas,
                    "Porción adicional de salsa."),
                Producto("Cilantro y cebolla", "$5", R.drawable.cilantro,
                    "Mezcla fresca de cilantro y cebolla picada."),
                Producto("Limones", "$5", R.drawable.limones,
                    "Porción de limones para acompañar.")
            )

            "DeliCrepas" -> listOf(
                Producto("Extra topping", "$15", R.drawable.toppings,
                    "Ingrediente adicional para crepas dulces o saladas."),
                Producto("Helado", "$20", R.drawable.helado,
                    "Bola de helado para acompañar."),
                Producto("Lechera", "$10", R.drawable.lechera,
                    "Porción de leche condensada.")
            )

            else -> listOf(
                Producto("Aguacate", "$15", R.drawable.rest1, "default")
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
        fun newInstance(nombre: String) = ComplementosFragment().apply {
            arguments = Bundle().apply {
                putString("nombre", nombre)
            }
        }
    }
}