package com.example.notes.mappers

import com.example.domainn.entity.NoteEntity
import com.example.notes.models.NoteUI

fun mapListToNoteUI(list:List<NoteEntity>): List<NoteUI> {

    val listNotesUI = mutableListOf<NoteUI>()

    list.forEach {
        listNotesUI.add(NoteUI(
            id = it.id,
            title = it.title,
            description = it.description,
            colorBackground = it.colorBackground,
            colorText = it.colorText,
            data = it.data,
            nameGroup = it.nameGroup
        ))
    }

    return listNotesUI
}

fun mapItemToNoteUI(noteEntity: NoteEntity) = NoteUI(
    id = noteEntity.id,
    title = noteEntity.title,
    description = noteEntity.description,
    colorBackground = noteEntity.colorBackground,
    colorText = noteEntity.colorText,
    data = noteEntity.data,
    nameGroup = noteEntity.nameGroup
)

