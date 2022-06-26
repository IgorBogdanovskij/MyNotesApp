package com.example.notes.features.notes.ui

import com.example.notes.features.notes.recycler.notes.NotesAdapter
import com.example.notes.models.NoteUi

class SimpleSelectionManager(private val notesAdapter: NotesAdapter) : SelectionManager {

    private val selectedItems = mutableListOf<NoteUi>()

    override fun select(noteUi: NoteUi) {
        selectedItems.add(noteUi)
        notesAdapter.submitList(notesAdapter.currentList.map {
            if (noteUi.id == it.id) it.copy(isFocusable = true) else it
        })
    }

    override fun unselect(noteUi: NoteUi) {
        selectedItems.removeIf { it.id == noteUi.id }
        notesAdapter.submitList(notesAdapter.currentList.map {
            if (noteUi.id == it.id) it.copy(isFocusable = false) else it
        })
    }

    override fun unselectAll() {
        selectedItems.clear()
        notesAdapter.submitList(notesAdapter.currentList.map { it.copy(isFocusable = false) })
    }

    override fun getAllSelectedItems(): List<NoteUi> = selectedItems

    override fun isAlreadyAdded(noteUi: NoteUi) = selectedItems.find { it.id == noteUi.id } != null

    override fun isEmpty() = selectedItems.isEmpty()
}
