package com.angel.appetito.ui

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.angel.appetito.R
import com.angel.appetito.database.DatabaseHelper
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.color.DynamicColors
import com.google.android.material.color.MaterialColors

class AddRestaurantActivity : AppCompatActivity() {

    private var imagenSeleccionada = 0
    private var imagenSeleccionadaUri: Uri? = null

    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                imagenSeleccionadaUri = uri
                findViewById<ImageView>(R.id.imgPreview).setImageURI(uri)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        DynamicColors.applyToActivityIfAvailable(this)
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_restaurant)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etDireccion = findViewById<EditText>(R.id.etDireccion)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)
        val btnImg = findViewById<Button>(R.id.btnSeleccionarImagen)
        val imgPreview = findViewById<ImageView>(R.id.imgPreview)

        val modo = intent.getStringExtra("modo")
        val esEdicion = modo == "editar"

        val toolbar = findViewById<MaterialToolbar>(R.id.materialToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.title = if (esEdicion) {
            "Editar restaurante"
        } else {
            "Agregar restaurante"
        }

        val color = MaterialColors.getColor(
            toolbar,
            com.google.android.material.R.attr.colorOnPrimaryContainer
        )
        toolbar.navigationIcon?.setTint(color)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (esEdicion) {
            etNombre.setText(intent.getStringExtra("nombre"))
            etDireccion.setText(intent.getStringExtra("direccion"))

            imagenSeleccionada = intent.getIntExtra("imagen", R.drawable.rest1)
            imgPreview.setImageResource(imagenSeleccionada)
        }

        btnImg.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        btnGuardar.setOnClickListener {

            val nombre = etNombre.text.toString()
            val direccion = etDireccion.text.toString()

            if (nombre.isEmpty() || direccion.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val dbHelper = DatabaseHelper(this)

            if (esEdicion) {

                val id = intent.getIntExtra("id", -1)

                dbHelper.updateRestaurant(
                    id,
                    nombre,
                    direccion,
                    imagenSeleccionada
                )

                Toast.makeText(this, "Actualizado", Toast.LENGTH_SHORT).show()

            } else {

                dbHelper.insertRestaurant(
                    dbHelper.writableDatabase,
                    nombre,
                    direccion,
                    imagenSeleccionada
                )

                Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show()
            }

            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}