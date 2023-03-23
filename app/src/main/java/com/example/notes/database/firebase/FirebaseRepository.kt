package com.example.notes.database.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.notes.database.DatabaseRepository
import com.example.notes.model.AppNote
import com.example.notes.utils.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FirebaseRepository(): DatabaseRepository {

    init {
        AUTH_FIREBASE = FirebaseAuth.getInstance()
    }

    override val allNotes: LiveData<List<AppNote>> = AllNoteLiveData()

    override suspend fun insertNote(note: AppNote, onSuccess: () -> Unit) {
        val idNote = REF_DB_FIREBASE.push().key.toString()
        val mapNote = hashMapOf<String, Any>()
        mapNote[ID_FIREBASE] = idNote
        mapNote[NAME] = note.name
        mapNote[TEXT] = note.text

        REF_DB_FIREBASE.child(idNote)
            .updateChildren(mapNote)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { it.message.toString() }
        Log.d(TAG, "insert to Firebase\n id note: ${mapNote[ID_FIREBASE]}\nTitle: ${mapNote[NAME]}\nText: ${mapNote[TEXT]}")
    }

    override suspend fun deleteNote(note: AppNote, onSuccess: () -> Unit) {
        Log.d(TAG, "Delete on Firebase\n id note: ${note.id_firebase}\nTitle: ${note.name}\nText: ${note.text}")
        REF_DB_FIREBASE.child(note.id_firebase).removeValue()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }
    }

    override fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {

        if (Preferences.getInitUser()){
            initReference()
            onSuccess()
        }
        else {
            AUTH_FIREBASE.signInWithEmailAndPassword(EMAIL, PASSWORD)
                .addOnSuccessListener {
                    initReference()
                    onSuccess() }
                .addOnFailureListener {
                    AUTH_FIREBASE.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                        .addOnSuccessListener {
                            initReference()
                            onSuccess() }
                        .addOnFailureListener { onFail(it.message.toString()) }
                }
        }
    }

    fun initReference(){
        CURRENT_ID = AUTH_FIREBASE.currentUser?.uid.toString()
        REF_DB_FIREBASE = FirebaseDatabase.getInstance().reference.child(CURRENT_ID)
    }

    override fun signOut() {
        AUTH_FIREBASE.signOut()
    }
}