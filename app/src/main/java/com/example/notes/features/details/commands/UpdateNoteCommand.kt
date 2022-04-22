package com.example.notes.features.details.commands

import com.example.notes.common.Command
import com.example.notes.databinding.FragmentNoteDetailsBinding
import com.example.notes.features.details.presentation.NoteDetailsViewModel
import com.example.notes.mappers.mapToNoteEntity
import com.example.notes.models.NoteUi

class UpdateNoteCommand(
    private val noteUi:NoteUi,
    private val viewModel: NoteDetailsViewModel,
    private val viewBinding: FragmentNoteDetailsBinding
) : Command {

    override fun execute() {
        if (viewBinding.autoCompleteTextViewWrite.text.toString().isNotEmpty()) {
            noteUi.nameGroup = viewBinding.autoCompleteTextViewWrite.text.toString()
            viewModel.updateNote(mapToNoteEntity(noteUi))
        }

        if (noteUi.title.lowercase() != viewBinding.editTextTitle.text.toString()
                .lowercase() ||
            noteUi.description.lowercase() != viewBinding.editTextTextDescription.text.toString()
                .lowercase()
        ) {
            noteUi.title = viewBinding.editTextTitle.text.toString()
            noteUi.description = viewBinding.editTextTextDescription.text.toString()
            viewModel.updateNote(mapToNoteEntity(noteUi))
        }
    }
}
