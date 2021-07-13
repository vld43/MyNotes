package com.example.nptes.mynotes.vld.screens.note

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.nptes.mynotes.vld.R
import com.example.nptes.mynotes.vld.databinding.FragmentMainBinding
import com.example.nptes.mynotes.vld.databinding.FragmentNoteBinding
import com.example.nptes.mynotes.vld.models.AppNote
import com.example.nptes.mynotes.vld.screens.main.MainAdapter
import com.example.nptes.mynotes.vld.screens.main.MainFragmentViewModel
import com.example.nptes.mynotes.vld.utilits.APP_ACTIVITY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteFragment : Fragment() {

    private var fragmentNoteBinding: FragmentNoteBinding? = null
    private val binding get() = fragmentNoteBinding!!

    private lateinit var viewModel: NoteFragmentViewModel

    private lateinit var currentNote: AppNote

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentNoteBinding = FragmentNoteBinding.inflate(layoutInflater, container, false)
        currentNote = arguments?.getSerializable("note") as AppNote
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentNoteBinding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_btn -> {
                viewModel.delete(currentNote) {
                    lifecycleScope.launch(Dispatchers.Main) {
                        APP_ACTIVITY.navController.navigate(R.id.action_noteFragment_to_mainFragment)
                    }
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initialization() {
        setHasOptionsMenu(true)

        binding.noteName.text = currentNote.name
        binding.noteText.text = currentNote.text

        viewModel = ViewModelProvider(this).get(NoteFragmentViewModel::class.java)
    }

}