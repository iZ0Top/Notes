package com.example.notes.utils

import com.example.notes.MainActivity
import com.example.notes.database.DatabaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

lateinit var APP_ACTIVITY: MainActivity
lateinit var REPOSITORY: DatabaseRepository

const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"
const val ID_FIREBASE = "id_firebase"
const val NAME = "name"
const val TEXT = "text"

lateinit var EMAIL: String
lateinit var PASSWORD: String

lateinit var AUTH_FIREBASE: FirebaseAuth
lateinit var CURRENT_ID: String
lateinit var REF_DB_FIREBASE: DatabaseReference

const val TAG = "log"
