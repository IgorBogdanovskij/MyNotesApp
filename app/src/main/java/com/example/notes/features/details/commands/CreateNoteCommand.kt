package com.example.notes.features.details.commands

import com.example.domainn.entity.NoteEntity
import com.example.notes.common.Command
import com.example.notes.databinding.FragmentNoteDetailsBinding
import com.example.notes.features.details.presentation.NoteDetailsViewModel
import java.util.*

class CreateNoteCommand(
    private val viewModel: NoteDetailsViewModel,
    private var viewBinding: FragmentNoteDetailsBinding
) : Command {

    override fun execute() {
        var nameGroup = ""
        if (viewBinding.editTextTitle.text.toString().isNotEmpty() &&
            viewBinding.editTextTextDescription.text.toString().isNotEmpty()
        ) {
            if (viewBinding.autoCompleteTextViewWrite.text.toString().isNotEmpty()) {
                nameGroup = viewBinding.autoCompleteTextViewWrite.text.toString()
            }
            createAndAddNote(nameGroup)
        }
    }

    private fun createAndAddNote(nameGroup: String) {
        viewModel.addNote(
            NoteEntity(
                viewBinding.editTextTitle.text.toString(),
                viewBinding.editTextTextDescription.text.toString(),
                0,
                0,
                Date(),
                nameGroup
            )
        )
    }
}
