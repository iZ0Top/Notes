package com.example.notes.utils

import android.content.Context
import android.content.SharedPreferences

object Preferences {

    private const val INIT_USER = "init_user"
    private const val TYPE_DB = "type_db"
    private const val NAME_PREFERENCE = "preference"

    private lateinit var mPreferences: SharedPreferences


    fun getPreferences(context: Context): SharedPreferences {
        mPreferences = context.getSharedPreferences(NAME_PREFERENCE, Context.MODE_PRIVATE)
        return mPreferences
    }


    fun setInitUser(init: Boolean){
        mPreferences.edit().putBoolean(INIT_USER, init).apply()
    }

    fun setTypeDatabase(type: String){
        mPreferences.edit().putString(TYPE_DB, type).apply()
    }

    fun getInitUser():Boolean{
        return mPreferences.getBoolean(INIT_USER, false)
    }

    fun  getTypeDatabase(): String{
        return mPreferences.getString(TYPE_DB, TYPE_ROOM).toString()
    }

}