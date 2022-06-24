package com.example.notes.features.notes.ui

import android.util.Log
import com.example.notes.features.notes.recycler.notes.NotesAdapter
import com.example.notes.models.NoteUi

class SimpleSelectionManager(private val notesAdapter: NotesAdapter) : SelectionManager {

    private val selectedItems = mutableListOf<NoteUi>()

    override fun select(noteUi: NoteUi) {
        selectedItems.add(noteUi.copy())
        notesAdapter.submitList(
            notesAdapter.currentList.map { if (noteUi.id == it.id) it.copy(isFocusable = true) else it }
        )
        notesAdapter.notifyDataSetChanged()
    }

    override fun unselect(noteUi: NoteUi) {
        selectedItems.removeIf { it.id == noteUi.id }
        val modifiedItems =
            notesAdapter.currentList.map { if (noteUi.id == it.id) it.copy(isFocusable = false) else it }
        notesAdapter.submitList(modifiedItems)
    }

    override fun unselectAll() {
        selectedItems.clear()
        val modifiedItems = notesAdapter.currentList.map { it.copy(isFocusable = false) }
        Log.d("lol", "unselectAll modifiedItems: $modifiedItems")
        notesAdapter.submitList(modifiedItems)
        Log.d("lol", "unselectAll: ${notesAdapter.currentList}")
    }

    override fun getAllSelectedItems(): List<NoteUi> = selectedItems

    override fun isAlreadyAdded(noteUi: NoteUi) = selectedItems.contains(noteUi)

    override fun isEmpty() = selectedItems.isEmpty()
}
