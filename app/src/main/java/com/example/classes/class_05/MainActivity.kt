package com.example.classes.class_05;

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.classes.R
import com.example.classes.databinding.Class05Binding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: Class05Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.class_05)
        binding = DataBindingUtil.setContentView(this, R.layout.class_05)
        setup_toolbar()
        setup_bottom_navigation()
    }

    private fun setup_drawer(toolbar: Toolbar) {
        val drawer_layout = binding.drawerLayout
        val navigation_view = binding.navigationView
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.class_05_open_drawer,
            R.string.class_05_close_drawer
        )

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        navigation_view.setNavigationItemSelectedListener {
            item ->
            when (item.itemId) {
                R.id.nav_item_1 -> {
                    val intent = Intent(this, TabLayoutExample::class.java)
                    startActivity(intent)
                    drawer_layout.closeDrawer(navigation_view)
                    true
                }
                R.id.nav_item_2 -> {
                    Toast.makeText(this, "item 2", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_item_3 -> {
                    Toast.makeText(this, "item 3", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

    private fun setup_toolbar() {
        val toolbar = binding.toolbar.toolbar
        setSupportActionBar(toolbar)
        setup_drawer(toolbar)
        toolbar.title = "toolbar title"
        toolbar.setOnMenuItemClickListener {
            item ->
            when (item.itemId) {
                R.id.toolbar_item_1 -> {
                    Toast.makeText(this, "item 1", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.toolbar_item_2 -> {
                    Toast.makeText(this, "item 2", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

    private fun setup_bottom_navigation() {
        val bottom_navigation = binding.bottomNavigation

        bottom_navigation.setOnItemSelectedListener {
            item ->
            when (item.itemId) {
                R.id.btn1 -> {
                    Toast.makeText(this, "item 1", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.btn2 -> {
                    Toast.makeText(this, "item 2", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.btn3 -> {
                    Toast.makeText(this, "item 3", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
}
