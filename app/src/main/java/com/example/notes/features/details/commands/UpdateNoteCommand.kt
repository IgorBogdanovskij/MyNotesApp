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
        updateNote()
    }

    private fun updateNote() {
        val newTitle = viewBinding.editTextTitle.text.toString()
        val newDescription = viewBinding.editTextTextDescription.text.toString()
        val newGroup = viewBinding.autoCompleteTextViewWrite.text.toString()

        val newNoteUi = noteUi.copy(
            title = newTitle,
            description = newDescription,
            group = newGroup
        )

        if (noteUi != newNoteUi) {
            viewModel.updateNote(mapToNoteEntity(newNoteUi))
        }
    }
}
