package com.angel.appetito.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.angel.appetito.R
import com.angel.appetito.adapter.MenuPagerAdapter
import com.angel.appetito.database.DatabaseHelper
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.color.DynamicColors
import com.google.android.material.color.MaterialColors
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        DynamicColors.applyToActivityIfAvailable(this)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        val id = intent.getIntExtra("id", -1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)

        viewPager.adapter = MenuPagerAdapter(this, id)

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

        supportActionBar?.title = "Menú"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val color = MaterialColors.getColor(
            toolbar,
            com.google.android.material.R.attr.colorOnPrimaryContainer
        )

        toolbar.navigationIcon?.setTint(color)

        val fab = findViewById<FloatingActionButton>(R.id.fabFood)

        fab.setOnClickListener {
            val intent = Intent(this, AddFoodActivity::class.java)
            intent.putExtra("restaurantId", id)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun filtrarEnFragmentoActual(texto: String) {
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val fragment = supportFragmentManager.findFragmentByTag("f" + viewPager.currentItem)

        when (fragment) {
            is ComidaFragment -> fragment.filtrar(texto)
            is BebidasFragment -> fragment.filtrar(texto)
            is ComplementosFragment -> fragment.filtrar(texto)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)

        val item = menu.findItem(R.id.action_search)
        val searchView = item.actionView as androidx.appcompat.widget.SearchView

        searchView.queryHint = "Buscar producto..."

        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filtrarEnFragmentoActual(newText ?: "")
                return true
            }
        })

        return true
    }

    override fun onResume() {
        super.onResume()

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        viewPager.adapter = MenuPagerAdapter(this, intent.getIntExtra("id", -1))
    }

}