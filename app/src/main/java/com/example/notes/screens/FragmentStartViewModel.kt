package com.example.notes.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.notes.database.firebase.FirebaseRepository
import com.example.notes.database.room.RoomDatabase
import com.example.notes.database.room.RoomRepository
import com.example.notes.utils.REPOSITORY
import com.example.notes.utils.TYPE_FIREBASE
import com.example.notes.utils.TYPE_ROOM
import com.example.notes.utils.showToast

class FragmentStartViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application

    fun  initDataBase(type: String, onSuccess: () -> Unit){

        when(type){
            TYPE_ROOM -> {
                val dao = RoomDatabase.getInstance(context).getRoomDao()
                REPOSITORY = RoomRepository(dao)
                onSuccess()
            }
            TYPE_FIREBASE -> {
                REPOSITORY = FirebaseRepository()
                REPOSITORY.connectToDatabase({onSuccess()}, { showToast(it) })
            }
        }
    }
}