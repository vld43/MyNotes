package com.example.nptes.mynotes.vld.screens.addNewNote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.nptes.mynotes.vld.models.AppNote
import com.example.nptes.mynotes.vld.utilits.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewNoteViewModel(application: Application) : AndroidViewModel(application) {

    fun insert(note: AppNote, onSuccess: () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.insert(note) {
                onSuccess()
            }
        }
}