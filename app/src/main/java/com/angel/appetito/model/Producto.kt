package com.angel.appetito.model

import com.angel.appetito.R

data class Producto(
    val nombre: String,
    val precio: String,
    val imagen: Int = R.drawable.bg_image
)
