package com.example.notes.features.notes.commands

import android.util.Log
import com.example.notes.R
import com.example.notes.common.Command
import com.example.notes.features.notes.presentation.NotesViewModel
import com.example.notes.features.notes.recycler.notes.NotesAdapter
import com.example.notes.features.notes.ui.NotesFragment
import com.example.notes.mappers.mapToNoteEntity
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class DeleteNoteBySwipeCommand(
    private val notesAdapter: NotesAdapter,
    private val notesFragment: NotesFragment,
    private val notesViewModel: NotesViewModel,
    private val adapterPosition: Int
): Command {

    override fun execute() {
        val note = notesAdapter.getNoteByPosition(adapterPosition)
        notesViewModel.deleteNote(note.id)
        Snackbar.make(
            notesFragment.requireView(),
            notesFragment.resources.getText(R.string.snack_bar_delete_message),
            BaseTransientBottomBar.LENGTH_LONG
        ).apply {
            setAction("Undo") { notesViewModel.addNote(mapToNoteEntity(note)) }
            show()
        }
    }
}
