package com.example.notes.models

import java.util.*

data class NoteUi(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val colorBackground: Int = 0,
    val colorText: Int = 0,
    val createDate: Date = Date(),
    val sortDate: Date = Date(),
    val group: String = "",
    val isFocusable: Boolean = false
)
