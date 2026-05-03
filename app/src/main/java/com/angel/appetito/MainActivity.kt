package com.angel.appetito

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.angel.appetito.adapter.RestauranteAdapter
import com.angel.appetito.model.Restaurante

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        val restaurantes = listOf(
            Restaurante("Tortas Ahogadas \"Al estilo Jalisco\"",
                "Av Revolucion Nte 123-A, Centro, CDMX", R.drawable.rest1),
            Restaurante("CherryBlossom - Sushi Place",
                "Av. Juarez 812, Centro, CDMX", R.drawable.rest2),
            Restaurante("Tacontento",
                "Barcelona 17-F, Centro, CDMX", R.drawable.rest3),
            Restaurante("DeliCrepas",
                "Sur 16 220, Agrícola Oriental, Iztacalco, CDMX", R.drawable.rest4)
        )

        val adapter = RestauranteAdapter(restaurantes)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}