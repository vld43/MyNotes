package com.example.nptes.mynotes.vld.database

import androidx.lifecycle.LiveData
import com.example.nptes.mynotes.vld.models.AppNote

interface DatabaseRepository {

    val allNotes: LiveData<List<AppNote>>

    suspend fun insert(note:AppNote, onSuccess:() -> Unit)

    suspend fun delete(note:AppNote, onSuccess:() -> Unit)
}