package com.example.nptes.mynotes.vld.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.nptes.mynotes.vld.R
import com.example.nptes.mynotes.vld.databinding.FragmentStartBinding
import com.example.nptes.mynotes.vld.utilits.TYPE_ROOM


class StartFragment : Fragment() {

    private var fragmentStartBinding: FragmentStartBinding? = null
    private val binding get() = fragmentStartBinding!!

    private lateinit var viewModel: StartFragmentViewModel

    private val roomBtn by lazy { requireView().findViewById<Button>(R.id.room_btn) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentStartBinding = FragmentStartBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        viewModel = ViewModelProvider(this).get(StartFragmentViewModel::class.java)

        roomBtn.setOnClickListener {
            viewModel.initDatabase(TYPE_ROOM)
        }
    }

}