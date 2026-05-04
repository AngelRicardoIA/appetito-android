package com.angel.appetito.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.angel.appetito.R
import com.angel.appetito.model.Producto
import com.angel.appetito.model.Restaurante

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    "appetito.db",
    null,
    1
) {

    override fun onCreate(db: SQLiteDatabase) {

        val createRestaurant = """
            CREATE TABLE restaurant (
                _id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                direccion TEXT,
                imagen INTEGER
            )
        """.trimIndent()

        val createFood = """
            CREATE TABLE food (
                _id INTEGER PRIMARY KEY AUTOINCREMENT,
                restaurant_id INTEGER,
                name TEXT NOT NULL,
                price REAL,
                imagen INTEGER,
                description TEXT,
                type TEXT CHECK(type IN ('food','drink','complement')),
                FOREIGN KEY (restaurant_id) REFERENCES restaurant(_id)
            )
        """.trimIndent()

        db.execSQL(createRestaurant)
        db.execSQL(createFood)

        // DATOS BASE
        val idTortas = insertRestaurant(
            db,
            "Tortas Ahogadas \"Al estilo Jalisco\"",
            "Av Revolucion Nte 123-A, Centro, CDMX",
            R.drawable.rest1
        )

        // Bebidas
        insertFood(db, idTortas, "Agua de jamaica", 25.0, R.drawable.jamaica,
            "Bebida preparada a base de flor de jamaica, ligeramente ácida y servida fría en vaso de 500 ml.", "drink")

        insertFood(db, idTortas, "Refresco", 22.0, R.drawable.coca,
            "Bebida carbonatada de 500 ml.", "drink")

        insertFood(db, idTortas, "Tejuino", 30.0, R.drawable.tejuino,
            "Bebida tradicional de maíz fermentado.", "drink")

        // Comida
        insertFood(db, idTortas, "Torta ahogada clásica", 65.0, R.drawable.rest1,
            "Birote con carnitas en salsa.", "food")

        insertFood(db, idTortas, "Torta de Camaron", 75.0, R.drawable.torta_camaron,
            "Birote con camarón.", "food")

        insertFood(db, idTortas, "Tacos dorados ahogados", 55.0, R.drawable.tacos_ahogados,
            "Tacos fritos con salsa.", "food")

        // Complementos
        insertFood(db, idTortas, "Extra salsa", 10.0, R.drawable.salsa,
            "Porción adicional de salsa picante.", "complement")

        insertFood(db, idTortas, "Cebolla curtida", 8.0, R.drawable.cebolla,
            "Cebolla preparada con limón y especias.", "complement")

        insertFood(db, idTortas, "Aguacate", 15.0, R.drawable.aguacate,
            "Porción de aguacate fresco en rebanadas.", "complement")

        // CherryBlossom
        val idSushi = insertRestaurant(
            db,
            "CherryBlossom - Sushi Place",
            "Av. Juarez 812, Centro, CDMX",
            R.drawable.rest2
        )

        // drinks
        insertFood(db, idSushi, "Té verde", 30.0, R.drawable.teverde, "Té caliente.", "drink")
        insertFood(db, idSushi, "Refresco", 25.0, R.drawable.coca, "Bebida.", "drink")
        insertFood(db, idSushi, "Ramune", 45.0, R.drawable.ramune, "Refresco japonés.", "drink")

        // food
        insertFood(db, idSushi, "Sushi roll California", 120.0, R.drawable.california, "Sushi clásico.", "food")
        insertFood(db, idSushi, "Sushi empanizado", 140.0, R.drawable.empanizado, "Sushi crujiente.", "food")
        insertFood(db, idSushi, "Yakimeshi mixto", 110.0, R.drawable.yakimeshi, "Arroz japonés.", "food")

        // complement
        insertFood(db, idSushi, "Soya extra", 5.0, R.drawable.soja, "Soya.", "complement")
        insertFood(db, idSushi, "Wasabi", 10.0, R.drawable.wasabi, "Wasabi.", "complement")
        insertFood(db, idSushi, "Jengibre", 10.0, R.drawable.jengibre, "Jengibre.", "complement")

    // Tacontento
        val idTacos = insertRestaurant(
            db,
            "Tacontento",
            "Barcelona 17-F, Centro, CDMX",
            R.drawable.rest3
        )

        // drinks
        insertFood(db, idTacos, "Horchata", 25.0, R.drawable.horchata, "Bebida de arroz.", "drink")
        insertFood(db, idTacos, "Refresco", 22.0, R.drawable.coca, "Bebida.", "drink")
        insertFood(db, idTacos, "Agua mineral", 20.0, R.drawable.mineral, "Agua con gas.", "drink")

        // food
        insertFood(db, idTacos, "Tacos al pastor (3)", 45.0, R.drawable.pastor, "Con piña.", "food")
        insertFood(db, idTacos, "Tacos de asada (3)", 55.0, R.drawable.asada, "Carne asada.", "food")
        insertFood(db, idTacos, "Gringa", 60.0, R.drawable.gringa, "Tortilla con queso.", "food")

        // complement
        insertFood(db, idTacos, "Salsa extra", 5.0, R.drawable.salsas, "Salsa.", "complement")
        insertFood(db, idTacos, "Cilantro y cebolla", 5.0, R.drawable.cilantro, "Mezcla.", "complement")
        insertFood(db, idTacos, "Limones", 5.0, R.drawable.limones, "Limón.", "complement")


        // DeliCrepas
        val idCrepas = insertRestaurant(
            db,
            "DeliCrepas",
            "Sur 16 220, Agrícola Oriental, CDMX",
            R.drawable.rest4
        )

        // drinks
        insertFood(db, idCrepas, "Café americano", 30.0, R.drawable.americano, "Café.", "drink")
        insertFood(db, idCrepas, "Capuccino", 45.0, R.drawable.cappuccino, "Café con leche.", "drink")
        insertFood(db, idCrepas, "Frappé", 55.0, R.drawable.frappe, "Bebida fría.", "drink")

        // food
        insertFood(db, idCrepas, "Crepa Nutella", 70.0, R.drawable.nutella, "Dulce.", "food")
        insertFood(db, idCrepas, "Crepa jamón y queso", 65.0, R.drawable.jamon, "Salada.", "food")
        insertFood(db, idCrepas, "Crepa fresa con crema", 75.0, R.drawable.fresa, "Fresa.", "food")

        // complement
        insertFood(db, idCrepas, "Extra topping", 15.0, R.drawable.toppings, "Extra.", "complement")
        insertFood(db, idCrepas, "Helado", 20.0, R.drawable.helado, "Helado.", "complement")
        insertFood(db, idCrepas, "Lechera", 10.0, R.drawable.lechera, "Leche.", "complement")
    }

    fun insertRestaurant(
        db: SQLiteDatabase,
        name: String,
        direccion: String,
        imagen: Int
    ): Long {
        val values = ContentValues()
        values.put("name", name)
        values.put("direccion", direccion)
        values.put("imagen", imagen)

        return db.insert("restaurant", null, values)
    }

    fun insertFood(
        db: SQLiteDatabase,
        restaurantId: Long,
        name: String,
        price: Double,
        imagen: Int,
        description: String,
        type: String
    ) {
        val values = ContentValues()
        values.put("restaurant_id", restaurantId)
        values.put("name", name)
        values.put("price", price)
        values.put("imagen", imagen)
        values.put("description", description)
        values.put("type", type)

        db.insert("food", null, values)
    }

    fun getRestaurants(): List<Restaurante> {

        val lista = mutableListOf<Restaurante>()
        val db = readableDatabase

        val cursor = db.rawQuery("SELECT * FROM restaurant", null)

        while (cursor.moveToNext()) {

            val id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"))
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val direccion = cursor.getString(cursor.getColumnIndexOrThrow("direccion"))
            val imagen = cursor.getInt(cursor.getColumnIndexOrThrow("imagen"))

            lista.add(
                Restaurante(id, name, direccion, imagen)
            )
        }

        cursor.close()

        return lista
    }

    fun getFoodByType(restaurantId: Int, type: String): List<Producto> {

        val lista = mutableListOf<Producto>()
        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM food WHERE restaurant_id = ? AND type = ?",
            arrayOf(restaurantId.toString(), type)
        )

        while (cursor.moveToNext()) {

            val id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"))
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"))
            val imagen = cursor.getInt(cursor.getColumnIndexOrThrow("imagen"))
            val description = cursor.getString(cursor.getColumnIndexOrThrow("description"))

            lista.add(
                Producto(id, name, "$$price", imagen, description)
            )
        }

        cursor.close()
        return lista
    }

    fun updateRestaurant(id: Int, nombre: String, direccion: String, imagen: Int) {
        val db = writableDatabase

        val values = android.content.ContentValues().apply {
            put("name", nombre)
            put("direccion", direccion)
            put("imagen", imagen)
        }

        db.update("restaurant", values, "_id = ?", arrayOf(id.toString()))
    }

    fun deleteRestaurant(id: Int) {
        val db = writableDatabase
        db.delete("restaurant", "_id = ?", arrayOf(id.toString()))
    }

    fun updateFood(
        id: Int,
        name: String,
        price: Double,
        imagen: Int,
        description: String,
        type: String
    ) {
        val db = writableDatabase

        val values = ContentValues().apply {
            put("name", name)
            put("price", price)
            put("imagen", imagen)
            put("description", description)
            put("type", type)
        }

        db.update("food", values, "_id = ?", arrayOf(id.toString()))
    }

    fun deleteFood(id: Int) {
        val db = writableDatabase
        db.delete("food", "_id = ?", arrayOf(id.toString()))
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS food")
        db.execSQL("DROP TABLE IF EXISTS restaurant")
        onCreate(db)
    }
}