package com.example.notes.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.notes.model.AppNote

class FragmentAddNoteViewModel(application: Application): AndroidViewModel(application) {

    fun insertToDatabase(note: AppNote, onSuccess:() -> Unit){
        

    }

}