package com.example.notes.mappers

import com.example.domainn.entity.NoteEntity
import com.example.notes.models.NoteUi

fun mapListToNoteUI(list: List<NoteEntity>): List<NoteUi> {

    return mutableListOf<NoteUi>().apply {
        list.forEach {
            add(
                NoteUi(
                    id = it.id,
                    title = it.title,
                    description = it.description,
                    colorBackground = it.colorBackground,
                    colorText = it.colorText,
                    createDate = it.createDate,
                    sortDate = it.sortDate,
                    group = it.nameGroup
                )
            )
        }
    }
}

fun mapItemToNoteUI(noteEntity: NoteEntity) = NoteUi(
    id = noteEntity.id,
    title = noteEntity.title,
    description = noteEntity.description,
    colorBackground = noteEntity.colorBackground,
    colorText = noteEntity.colorText,
    createDate = noteEntity.createDate,
    sortDate = noteEntity.sortDate,
    group = noteEntity.nameGroup
)
