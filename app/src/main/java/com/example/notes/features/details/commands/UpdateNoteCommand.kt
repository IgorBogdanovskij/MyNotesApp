package com.example.notes.features.details.commands

import com.example.notes.common.Command
import com.example.notes.databinding.FragmentNoteDetailsBinding
import com.example.notes.features.details.presentation.NoteDetailsViewModel
import com.example.notes.mappers.mapToNoteEntity
import com.example.notes.models.NoteUi

class UpdateNoteCommand(
    private val noteUi: NoteUi,
    private val viewModel: NoteDetailsViewModel,
    private val viewBinding: FragmentNoteDetailsBinding
) : Command {

    override fun execute() {

        val newGroup = viewBinding.autoCompleteTextViewWrite.text.toString()
        val newTitle = viewBinding.editTextTitle.text.toString()
        val newDescription = viewBinding.editTextTextDescription.text.toString()
        val newNoteUi = noteUi.copy()

        updateGroup(newGroup, newNoteUi)
        updateNoteEntry(newTitle, newDescription, newNoteUi)
        updateNote(newNoteUi)

    }

    private fun updateNote(newNote: NoteUi) {
        if (noteUi != newNote) {
            viewModel.updateNote(mapToNoteEntity(newNote))
        }
    }

    private fun updateNoteEntry(newTitle: String, newDescription: String, newNote: NoteUi) {
        if (checkTheSameNote(newTitle, newDescription, newNote)) {
            newNote.title = newTitle
            newNote.description = newDescription
        }
    }

    private fun updateGroup(newGroup: String, newNote: NoteUi) {
        if (newGroup.isNotEmpty()) {
            newNote.nameGroup = newGroup
        }
    }

    private fun checkTheSameNote(newTitle: String, newDescription: String, newNote: NoteUi): Boolean {
        return newNote.title.lowercase() != newTitle ||
                newNote.description.lowercase() != newDescription
    }
}
