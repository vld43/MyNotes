package com.example.nptes.mynotes.vld.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.nptes.mynotes.vld.database.room.AppRoomDatabase
import com.example.nptes.mynotes.vld.database.room.AppRoomRepository
import com.example.nptes.mynotes.vld.utilits.REPOSITORY
import com.example.nptes.mynotes.vld.utilits.TYPE_ROOM

class StartFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application

    fun initDatabase(type: String, onSuccess:() -> Unit) {
        when(type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context).getAppRoomDao()
                REPOSITORY = AppRoomRepository(dao)
                onSuccess()
            }
        }
    }
}