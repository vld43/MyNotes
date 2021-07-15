package com.example.nptes.mynotes.vld.screens.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.nptes.mynotes.vld.R
import com.example.nptes.mynotes.vld.databinding.FragmentMainBinding
import com.example.nptes.mynotes.vld.models.AppNote
import com.example.nptes.mynotes.vld.utilits.APP_ACTIVITY
import com.example.nptes.mynotes.vld.utilits.AppPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    private var mainFragmentBinding: FragmentMainBinding? = null
    private val binding get() = mainFragmentBinding!!

    private lateinit var viewModel: MainFragmentViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MainAdapter

    private lateinit var observerList: Observer<List<AppNote>>

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
        viewModel.allNotes.removeObserver(observerList)
        recyclerView.adapter = null
    }

    private fun initialization() {
        setHasOptionsMenu(true)

        adapter = MainAdapter()
        recyclerView = binding.recyclerView
        recyclerView.adapter = adapter

        observerList = Observer {
            adapter.setList(it.asReversed())
        }

        viewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)
        viewModel.allNotes.observe(this, observerList)

        binding.addNoteFab.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_addNewNoteFragment)
        }

    }

    companion object {

        fun click(note: AppNote) {
            val bundle = Bundle()
            bundle.putSerializable("note", note)

            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_noteFragment, bundle)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exit_btn -> {
                viewModel.signOut()
                AppPreference.setInitUser(false)
                lifecycleScope.launch(Dispatchers.Main) {
                    APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_startFragment)
                }

            }
        }

        return super.onOptionsItemSelected(item)
    }

}