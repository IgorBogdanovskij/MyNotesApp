package com.example.notes.features.details.commands

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.example.notes.common.Command
import com.example.notes.databinding.FragmentNoteDetailsBinding
import com.example.notes.features.details.presentation.NoteDetailsViewModel
import com.example.notes.models.NoteUi
import com.example.notes.utility.executeCommand

class ApplyChangesCommand(
    private val noteId: Int,
    private val noteUi: NoteUi?,
    private val context: Context,
    private val navController: NavController,
    private val viewModel: NoteDetailsViewModel,
    private var viewBinding: FragmentNoteDetailsBinding
) : Command {

    override fun execute() {
        if (noteId != 0) {
            updateNote()
        } else {
            createNote()
        }
    }

    private fun createNote() {
        if (checkEmptyNote()) {
            Toast.makeText(context, "The note is empty", Toast.LENGTH_SHORT).show()
        } else {
            executeCommand(CreateNoteCommand(viewModel, viewBinding))
            navController.popBackStack()
        }
    }

    private fun updateNote() {
        executeCommand(UpdateNoteCommand(noteUi!!, viewModel, viewBinding))
        navController.popBackStack()
    }

    private fun checkEmptyNote() = viewBinding.editTextTitle.text.toString().isEmpty() &&
            viewBinding.editTextTextDescription.text.toString().isEmpty()
}
