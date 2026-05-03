package com.angel.appetito.ui

import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.angel.appetito.R
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.color.DynamicColors
import com.google.android.material.color.MaterialColors

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        DynamicColors.applyToActivityIfAvailable(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        val toolbar = findViewById<MaterialToolbar>(R.id.materialToolbar)
        setSupportActionBar(toolbar)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nombre = intent.getStringExtra("nombre")
        val precio = intent.getStringExtra("precio")
        val imagen = intent.getIntExtra("imagen", 0)
        val descripcion = intent.getStringExtra("descripcion")
        supportActionBar?.title = nombre

        val img = findViewById<ImageView>(R.id.imagenProducto)
        val txtNombre = findViewById<TextView>(R.id.nombreProducto)
        val txtPrecio = findViewById<TextView>(R.id.precioProducto)
        val txtDesc = findViewById<TextView>(R.id.descripcionProducto)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        img.setImageResource(imagen)
        txtNombre.text = nombre
        txtPrecio.text = precio
        txtDesc.text = descripcion
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}