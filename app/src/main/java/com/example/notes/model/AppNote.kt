package com.example.notes.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class AppNote(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val id_firebase: String = "none",
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "text") var text: String = ""
): java.io.Serializable
