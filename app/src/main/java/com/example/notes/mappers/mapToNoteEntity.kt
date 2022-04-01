package com.example.notes.mappers

import com.example.domainn.entity.NoteEntity
import com.example.notes.models.NoteUi


fun mapListToNoteEntity(list:List<NoteUi>): List<NoteEntity> {

    val listNotesUI = mutableListOf<NoteEntity>()

    list.forEach {
        NoteUi(
            id = it.id,
            title = it.title,
            description = it.description,
            colorBackground = it.colorBackground,
            colorText = it.colorText,
            data = it.data,
            nameGroup = it.nameGroup
        )
    }

    return listNotesUI
}

fun mapToNoteEntity(noteUi: NoteUi) =
    NoteEntity(
        title = noteUi.title,
        description = noteUi.description,
        colorBackground = noteUi.colorBackground,
        colorText = noteUi.colorText,
        data = noteUi.data,
        nameGroup = noteUi.nameGroup
    ).apply { id = noteUi.id }