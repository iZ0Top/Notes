package com.example.notes.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.model.AppNote
import com.example.notes.utils.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FragmentAddNoteViewModel(application: Application): AndroidViewModel(application) {

    fun insertToDatabase(note: AppNote, onSuccess:() -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.insertNote(note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
}