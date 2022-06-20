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
        val group = viewBinding.autoCompleteTextViewWrite.text.toString()
        createAndAddNote(group)
    }

    private fun createAndAddNote(nameGroup: String) {
        viewModel.addNote(
            NoteEntity(
                title = viewBinding.editTextTitle.text.toString(),
                description = viewBinding.editTextTextDescription.text.toString(),
                colorBackground = 0,
                colorText = 0,
                createDate = Date(),
                sortDate = Date(),
                nameGroup = nameGroup
            )
        )
    }
}
