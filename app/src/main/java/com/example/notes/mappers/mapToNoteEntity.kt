package com.example.notes.mappers

import com.example.domainn.entity.NoteEntity
import com.example.notes.models.NoteUi

fun mapListToNoteEntity(list: List<NoteUi>): List<NoteEntity> {

    return mutableListOf<NoteEntity>().apply {
        list.forEach {
            add(NoteEntity(
                title = it.title,
                description = it.description,
                colorBackground = it.colorBackground,
                colorText = it.colorText,
                createDate = it.createDate,
                sortDate = it.sortDate,
                nameGroup = it.group
            ).apply { id = it.id })
        }
    }
}

fun mapToNoteEntity(noteUi: NoteUi) =
    NoteEntity(
        title = noteUi.title,
        description = noteUi.description,
        colorBackground = noteUi.colorBackground,
        colorText = noteUi.colorText,
        createDate = noteUi.createDate,
        sortDate = noteUi.sortDate,
        nameGroup = noteUi.group
    ).apply { id = noteUi.id }