package com.example.notes.features.notes.commands

import androidx.core.view.accessibility.AccessibilityEventCompat.setAction
import com.example.notes.R
import com.example.notes.common.Command
import com.example.notes.features.notes.presentation.NotesViewModel
import com.example.notes.features.notes.ui.NotesFragment
import com.example.notes.mappers.mapToNoteEntity
import com.example.notes.models.NoteUi
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class DeleteNoteCommand(
    private val noteUi: NoteUi,
    private val notesViewModel: NotesViewModel,
    private val notesFragment: NotesFragment
) : Command {

    override fun execute() {
        notesViewModel.deleteNote(noteUi.id)
        Snackbar.make(
            notesFragment.requireView(),
            notesFragment.resources.getText(R.string.snack_bar_delete_message),
            BaseTransientBottomBar.LENGTH_LONG
        ).apply {
            setAction("Undo") { notesViewModel.addNote(mapToNoteEntity(noteUi)) }
            show()
        }
    }
}
