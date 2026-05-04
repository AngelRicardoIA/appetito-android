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
import com.angel.appetito.database.DatabaseHelper
import com.angel.appetito.model.Producto

class BebidasFragment : Fragment() {

    lateinit var adapter: ProductoAdapter
    private var restaurantId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restaurantId = arguments?.getInt("id") ?: -1
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

        val dbHelper = DatabaseHelper(requireContext())
        val lista = dbHelper.getFoodByType(restaurantId, "drink")

        adapter = ProductoAdapter(lista)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    fun filtrar(texto: String) {
        adapter.filtrar(texto)
    }

    companion object {
        fun newInstance(id: Int) = BebidasFragment().apply {
            arguments = Bundle().apply {
                putInt("id", id)
            }
        }
    }
}