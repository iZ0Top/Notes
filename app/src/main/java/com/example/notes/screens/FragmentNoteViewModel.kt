package com.example.notes.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.model.AppNote
import com.example.notes.utils.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FragmentNoteViewModel(application: Application): AndroidViewModel(application) {


    fun deleteNote(note: AppNote, onSuccess:() -> Unit) = viewModelScope.launch(Dispatchers.IO){
        REPOSITORY.deleteNote(note){
            viewModelScope.launch(Dispatchers.Main){
                onSuccess()
            }
        }
    }
}