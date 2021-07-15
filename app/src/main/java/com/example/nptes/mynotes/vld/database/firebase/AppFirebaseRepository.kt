package com.example.nptes.mynotes.vld.database.firebase

import androidx.lifecycle.LiveData
import com.example.nptes.mynotes.vld.database.DatabaseRepository
import com.example.nptes.mynotes.vld.models.AppNote
import com.example.nptes.mynotes.vld.utilits.EMAIL
import com.example.nptes.mynotes.vld.utilits.PASSWORD
import com.google.firebase.auth.FirebaseAuth

class AppFirebaseRepository : DatabaseRepository {

    private val auth = FirebaseAuth.getInstance()

    override val allNotes: LiveData<List<AppNote>>
        get() = TODO("Not yet implemented")

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        auth.signInWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                auth.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onFail(it.message.toString()) }
            }
    }

    override fun signOut() {
        auth.signOut()
    }
}