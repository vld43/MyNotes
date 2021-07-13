package com.example.nptes.mynotes.vld.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.nptes.mynotes.vld.R
import com.example.nptes.mynotes.vld.databinding.FragmentMainBinding
import com.example.nptes.mynotes.vld.utilits.APP_ACTIVITY

class MainFragment : Fragment() {

    private var mainFragmentBinding: FragmentMainBinding? = null
    private val binding get() = mainFragmentBinding!!

    private lateinit var viewModel: MainFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainFragmentBinding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mainFragmentBinding = null
    }

    private fun initialization() {
        viewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)

        binding.addNoteFab.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_addNewNoteFragment)
        }
    }


}