package com.angel.appetito.ui

import android.net.Uri
import android.os.Bundle
import android.widget.*
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

class AddFoodActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_add_food)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etPrecio = findViewById<EditText>(R.id.etPrecio)
        val etDescripcion = findViewById<EditText>(R.id.etDescripcion)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)
        val btnImg = findViewById<Button>(R.id.btnSeleccionarImagen)
        val imgPreview = findViewById<ImageView>(R.id.imgPreview)
        val spinner = findViewById<Spinner>(R.id.spTipo)

        val modo = intent.getStringExtra("modo")
        val esEdicion = modo == "editar"

        val tipos = arrayOf("food", "drink", "complement")
        val adapterSpinner = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            tipos
        )
        spinner.adapter = adapterSpinner

        val toolbar = findViewById<MaterialToolbar>(R.id.materialToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.title = if (esEdicion) "Editar alimento" else "Agregar alimento"

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
            etPrecio.setText(intent.getStringExtra("precio")?.replace("$", ""))
            etDescripcion.setText(intent.getStringExtra("descripcion"))

            imagenSeleccionada = intent.getIntExtra("imagen", R.drawable.rest1)
            imgPreview.setImageResource(imagenSeleccionada)

            val tipo = intent.getStringExtra("tipo") ?: "food"
            val index = tipos.indexOf(tipo)
            if (index >= 0) spinner.setSelection(index)
        }

        btnImg.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        btnGuardar.setOnClickListener {

            val nombre = etNombre.text.toString()
            val precio = etPrecio.text.toString()
            val descripcion = etDescripcion.text.toString()
            val tipo = spinner.selectedItem.toString()

            if (nombre.isEmpty() || precio.isEmpty()) {
                Toast.makeText(this, "Completa los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val dbHelper = DatabaseHelper(this)

            if (esEdicion) {

                val id = intent.getIntExtra("id", -1)

                dbHelper.updateFood(
                    id,
                    nombre,
                    precio.toDouble(),
                    imagenSeleccionada,
                    descripcion,
                    tipo
                )

                Toast.makeText(this, "Actualizado", Toast.LENGTH_SHORT).show()

            } else {

                val restaurantId = intent.getIntExtra("restaurantId", -1)

                dbHelper.insertFood(
                    dbHelper.writableDatabase,
                    restaurantId.toLong(),
                    nombre,
                    precio.toDouble(),
                    imagenSeleccionada,
                    descripcion,
                    tipo
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