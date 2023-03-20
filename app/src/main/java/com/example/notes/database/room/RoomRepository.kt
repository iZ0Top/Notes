package com.example.notes.database.room

import androidx.lifecycle.LiveData
import com.example.notes.database.DatabaseRepository
import com.example.notes.model.AppNote

class RoomRepository(private val roomDao: RoomDao): DatabaseRepository {

    override val allNotes: LiveData<List<AppNote>>
        get() = roomDao.getAllNotes()

    override suspend fun insertNote(note: AppNote, onSuccess: () -> Unit) {
        roomDao.insertNote(note)
    }

    override suspend fun deleteNote(note: AppNote, onSuccess: () -> Unit) {
        roomDao.deleteNote(note)
    }
}