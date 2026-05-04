package com.angel.appetito.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.angel.appetito.R
import com.angel.appetito.adapter.ProductoAdapter
import com.angel.appetito.database.DatabaseHelper
import com.angel.appetito.model.Producto

class ComidaFragment : Fragment(R.layout.fragment_comida) {

    private var restaurantId: Int = -1
    private lateinit var adapter: ProductoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restaurantId = arguments?.getInt("id") ?: -1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerProductos)

        val dbHelper = DatabaseHelper(requireContext())
        val lista = dbHelper.getFoodByType(restaurantId, "food")

        adapter = ProductoAdapter(lista)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    fun filtrar(texto: String) {
        adapter.filtrar(texto)
    }

    companion object {
        fun newInstance(id: Int) = ComidaFragment().apply {
            arguments = Bundle().apply {
                putInt("id", id)
            }
        }
    }
}