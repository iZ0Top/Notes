package com.example.notes.database.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.notes.model.AppNote
import com.example.notes.utils.REF_DB_FIREBASE
import com.example.notes.utils.TAG
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AllNoteLiveData: LiveData<List<AppNote>>()  {

    private val listener = object: ValueEventListener {

        override fun onDataChange(snapshot: DataSnapshot) {
            value = snapshot.children.map {
                it.getValue(AppNote::class.java)?: AppNote()
            }

            Log.d(TAG, "AllNoteLiveData. onDataChange")
            for (note in value!!){
                Log.d(TAG, "idFirebase: ${note.id_firebase}, Name: ${note.name}, Text: ${note.text}" )
            }
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }
    }
    override fun onActive() {
        super.onActive()
        REF_DB_FIREBASE.addValueEventListener(listener)

    }

    override fun onInactive() {
        super.onInactive()
        REF_DB_FIREBASE.removeEventListener(listener)
    }
}