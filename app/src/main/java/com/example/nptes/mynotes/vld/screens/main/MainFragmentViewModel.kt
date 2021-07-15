package com.example.nptes.mynotes.vld.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.nptes.mynotes.vld.utilits.REPOSITORY

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {

    val allNotes = REPOSITORY.allNotes

    fun signOut() {
        REPOSITORY.signOut()
    }
}