package com.example.notes.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.notes.utils.REPOSITORY

class FragmentMainViewModel(application: Application): AndroidViewModel(application) {

    val allNotes = REPOSITORY.allNotes

    fun signOut(){
        REPOSITORY.signOut()
    }

}