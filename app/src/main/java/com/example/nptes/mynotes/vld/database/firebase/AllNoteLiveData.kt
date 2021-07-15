package com.example.nptes.mynotes.vld.database.firebase

import androidx.lifecycle.LiveData
import com.example.nptes.mynotes.vld.models.AppNote
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AllNoteLiveData : LiveData<List<AppNote>>() {

    private val auth = FirebaseAuth.getInstance()
    private val databaseReference = FirebaseDatabase.getInstance()
        .reference
        .child(auth.currentUser?.uid.toString())

    private val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            value = snapshot.children.map {
                it.getValue(AppNote::class.java) ?: AppNote()
            }
        }

        override fun onCancelled(error: DatabaseError) {

        }
    }

    override fun onActive() {
        databaseReference.addValueEventListener(listener)
        super.onActive()
    }

    override fun onInactive() {
        databaseReference.removeEventListener(listener)
        super.onInactive()
    }
}