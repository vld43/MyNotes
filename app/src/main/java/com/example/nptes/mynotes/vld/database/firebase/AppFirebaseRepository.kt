package com.example.nptes.mynotes.vld.database.firebase

import androidx.lifecycle.LiveData
import com.example.nptes.mynotes.vld.database.DatabaseRepository
import com.example.nptes.mynotes.vld.models.AppNote
import com.example.nptes.mynotes.vld.utilits.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AppFirebaseRepository : DatabaseRepository {

    private val auth = FirebaseAuth.getInstance()
    private val databaseReference = FirebaseDatabase.getInstance()
        .reference
        .child(auth.currentUser?.uid.toString())

    override val allNotes: LiveData<List<AppNote>> = AllNoteLiveData()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        val idNote = databaseReference.push().key.toString()
        val mapNote = hashMapOf<String, Any>()

        mapNote[ID_FIREBASE] = idNote
        mapNote[NAME] = note.name
        mapNote[TEXT] = note.text

        databaseReference.child(idNote)
            .updateChildren(mapNote)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }
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