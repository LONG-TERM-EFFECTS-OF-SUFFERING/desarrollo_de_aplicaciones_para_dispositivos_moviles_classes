package com.example.classes.class_05

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.classes.R
import com.example.classes.databinding.Class05TabLayoutExampleBinding
import com.google.android.material.tabs.TabLayoutMediator


class TabLayoutExample : AppCompatActivity() {
    private lateinit var binding: Class05TabLayoutExampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.class_05_tab_layout_example)
        binding = DataBindingUtil.setContentView(this, R.layout.class_05_tab_layout_example)
        setup_toobar()
        setup_tabs()
    }

    private fun setup_toobar() {
        val toolbar = binding.toolbar.toolbar
        toolbar.title = "Tab layout example"
        toolbar.setNavigationOnClickListener{ onBackPressed() }
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

    private fun setup_tabs() {
        val view_pager = binding.viewPager
        view_pager.adapter = ViewPagerAdapter(this)

        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, view_pager) { tab, position ->
            when (position) {
                0 -> tab.text = "Chats"
                1 -> tab.text = "News"
            }
        }.attach()
    }
}