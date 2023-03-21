package com.example.notes.database

import androidx.lifecycle.LiveData
import com.example.notes.model.AppNote

interface DatabaseRepository {

    val allNotes: LiveData<List<AppNote>>

    suspend fun insertNote(note: AppNote, onSuccess: ()-> Unit)

    suspend fun deleteNote(note: AppNote, onSuccess: ()-> Unit)

}