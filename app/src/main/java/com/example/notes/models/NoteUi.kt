package com.example.notes.models

import java.util.*

data class NoteUi(
    var id: Int = 0,
    var title: String = "",
    var description: String = "",
    var colorBackground: Int = 0,
    var colorText: Int = 0,
    var data: Date = Date(),
    var group: String = "",
)
