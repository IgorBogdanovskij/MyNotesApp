package com.example.domainn.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = NoteEntity.NAME_TABLE)
data class NoteEntity(
    var title: String = "",
    var description: String = "",
    var colorBackground: Int = 0,
    var colorText: Int = 0,
    var data: Date,
    var nameGroup: String = "",
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    companion object{
        const val NAME_TABLE = "notes_table"
    }
}

