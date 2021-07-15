package com.example.nptes.mynotes.vld.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.nptes.mynotes.vld.R
import com.example.nptes.mynotes.vld.databinding.FragmentStartBinding
import com.example.nptes.mynotes.vld.utilits.*


class StartFragment : Fragment() {

    private var fragmentStartBinding: FragmentStartBinding? = null
    private val binding get() = fragmentStartBinding!!

    private lateinit var viewModel: StartFragmentViewModel

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

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentStartBinding = null
    }

    private fun initialization() {
        viewModel = ViewModelProvider(this).get(StartFragmentViewModel::class.java)

        binding.roomBtn.setOnClickListener {
            viewModel.initDatabase(TYPE_ROOM) {
                APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
            }
        }

        binding.firebaseBtn.setOnClickListener {
            binding.inputEmail.visibility = View.VISIBLE
            binding.inputPassword.visibility = View.VISIBLE
            binding.loginBtn.visibility = View.VISIBLE

            binding.loginBtn.setOnClickListener {
                val inputEmail = binding.inputEmail.text.toString()
                val inputPassword = binding.inputPassword.text.toString()

                if(inputEmail.isNotEmpty() && inputPassword.isNotEmpty()) {
                    EMAIL = inputEmail
                    PASSWORD = inputPassword

                    viewModel.initDatabase(TYPE_FIREBASE) {
                        showToast("INIT OK")
                        //APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
                    }
                } else {
                    showToast(getString(R.string.toast_wrong_enter))
                }
            }
        }
    }

}