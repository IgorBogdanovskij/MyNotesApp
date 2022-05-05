package com.example.notes.mappers

import com.example.domainn.entity.NoteEntity
import com.example.notes.models.NoteUi

fun mapListToNoteUI(list:List<NoteEntity>): List<NoteUi> {

    val listNotesUI = mutableListOf<NoteUi>()

    list.forEach {
        listNotesUI.add(NoteUi(
            id = it.id,
            title = it.title,
            description = it.description,
            colorBackground = it.colorBackground,
            colorText = it.colorText,
            data = it.data,
            group = it.nameGroup
        ))
    }

    return listNotesUI
}

fun mapItemToNoteUI(noteEntity: NoteEntity) = NoteUi(
    id = noteEntity.id,
    title = noteEntity.title,
    description = noteEntity.description,
    colorBackground = noteEntity.colorBackground,
    colorText = noteEntity.colorText,
    data = noteEntity.data,
    group = noteEntity.nameGroup
)

