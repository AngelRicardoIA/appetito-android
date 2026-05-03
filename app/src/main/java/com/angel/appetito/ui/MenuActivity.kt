package com.angel.appetito.ui

import android.os.Bundle
import android.view.Menu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.angel.appetito.R
import com.angel.appetito.adapter.MenuPagerAdapter
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.color.DynamicColors
import com.google.android.material.color.MaterialColors
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        DynamicColors.applyToActivityIfAvailable(this)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        val nombre = intent.getStringExtra("nombre")
        val nombreRestaurante = nombre ?: ""
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)

        viewPager.adapter = MenuPagerAdapter(this, nombreRestaurante)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->

            tab.text = when (position) {
                0 -> "Comida"
                1 -> "Bebidas"
                2 -> "Complementos"
                else -> ""
            }

        }.attach()

        val toolbar = findViewById<MaterialToolbar>(R.id.materialToolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = nombre
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val color = MaterialColors.getColor(
            toolbar,
            com.google.android.material.R.attr.colorOnPrimaryContainer
        )

        toolbar.navigationIcon?.setTint(color)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

}