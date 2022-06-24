package com.example.notes.features.notes.ui

import com.example.notes.models.NoteUi

interface SelectionManager {
    fun select(noteUi: NoteUi)
    fun unselect(noteUi: NoteUi)
    fun unselectAll()
    fun getAllSelectedItems(): List<NoteUi>
    fun isAlreadyAdded(noteUi: NoteUi): Boolean
    fun isEmpty(): Boolean
}
