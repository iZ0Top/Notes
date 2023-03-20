package com.example.notes.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.notes.model.AppNote

@Database(entities = [AppNote::class], version = 1)
abstract class RoomDatabase: androidx.room.RoomDatabase() {
    abstract fun getRoomDao(): RoomDao

    companion object{

        @Volatile
        private var database: RoomDatabase? = null                                                  //створюємо

        @Synchronized
        fun getInstance(context: Context): RoomDatabase{
            return if (database == null){
                database = Room.databaseBuilder(context, RoomDatabase::class.java, "database").build()
                database as RoomDatabase
            } else database as RoomDatabase
        }
    }
}