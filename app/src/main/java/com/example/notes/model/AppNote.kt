package com.example.notes.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class AppNote(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val id_firebase: String = "none",
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "text") val text: String = ""
): java.io.Serializable
