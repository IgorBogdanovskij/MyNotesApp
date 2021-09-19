package com.example.notes.data.locale.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class NoteEntityKotlin(
    var title: String = "",
    var description: String = "",
    var colorBackground: Int = 0,
    var colorText: Int = 0,
    var data: Date,
    var nameGroup: String = "",
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}