package com.angel.appetito.model

import com.angel.appetito.R

data class Restaurante(
    val nombre: String,
    val direccion: String,
    val imagen: Int = R.drawable.bg_image
)
