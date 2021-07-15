package com.example.nptes.mynotes.vld.screens.addNewNote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.nptes.mynotes.vld.R
import com.example.nptes.mynotes.vld.databinding.FragmentAddNewNoteBinding
import com.example.nptes.mynotes.vld.models.AppNote
import com.example.nptes.mynotes.vld.utilits.APP_ACTIVITY
import com.example.nptes.mynotes.vld.utilits.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewNoteFragment : Fragment() {

    private var addNewNoteBinding: FragmentAddNewNoteBinding? = null
    private val binding get() = addNewNoteBinding!!

    private lateinit var viewModel: AddNewNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addNewNoteBinding = FragmentAddNewNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        addNewNoteBinding = null
    }

    private fun initialization() {
        viewModel = ViewModelProvider(this).get(AddNewNoteViewModel::class.java)
        binding.addNotBtn.setOnClickListener {
            val name = binding.inputNameNote.text.toString()
            val text = binding.inputTextNote.text.toString()

            if (name.isEmpty()) {
                showToast(getString(R.string.toast_enter_name))
            } else {
                viewModel.insert(AppNote(name = name, text = text)) {
                    lifecycleScope.launch(Dispatchers.Main) {
                        APP_ACTIVITY.navController.navigate(R.id.action_addNewNoteFragment_to_mainFragment)
                    }
                }
            }
        }
    }


}