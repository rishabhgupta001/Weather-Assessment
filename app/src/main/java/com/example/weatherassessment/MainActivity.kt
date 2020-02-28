package com.example.weatherassessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.weatherassessment.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    private lateinit var bindingView: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingView = DataBindingUtil.setContentView(this, R.layout.activity_main)

        init()
    }

    /**
     * inital setUps
     */
    private fun init() {
        val findNavController = findNavController(R.id.nav_host_fragment)

        // Setup Action Bar
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(findNavController)

        // Setup Toolbar
        toolbar.setupWithNavController(findNavController)

        toolbar.title = ""
        toolbar.toolbar_title.text = getString(R.string.text_title_forecast)
    }


}
