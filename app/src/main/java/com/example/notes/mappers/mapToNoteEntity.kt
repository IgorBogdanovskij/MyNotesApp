package com.example.notes.mappers

import com.example.domainn.entity.NoteEntity
import com.example.notes.models.NoteUI


fun mapListToNoteEntity(list:List<NoteUI>): List<NoteEntity> {

    val listNotesUI = mutableListOf<NoteEntity>()

    list.forEach {
        NoteUI(
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

fun mapToNoteEntity(noteUI: NoteUI) =
    NoteEntity(
        title = noteUI.title,
        description = noteUI.description,
        colorBackground = noteUI.colorBackground,
        colorText = noteUI.colorText,
        data = noteUI.data,
        nameGroup = noteUI.nameGroup
    ).apply { id = noteUI.id }