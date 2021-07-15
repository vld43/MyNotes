package com.example.nptes.mynotes.vld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.nptes.mynotes.vld.databinding.ActivityMainBinding
import com.example.nptes.mynotes.vld.utilits.APP_ACTIVITY
import com.example.nptes.mynotes.vld.utilits.AppPreference

class MainActivity : AppCompatActivity() {

    lateinit var toolBar: Toolbar
    lateinit var navController: NavController

    private var _binding: ActivityMainBinding? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        APP_ACTIVITY = this

        toolBar = binding.toolbar
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        setSupportActionBar(toolBar)
        title = getString(R.string.title)

        AppPreference.getPreference(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}