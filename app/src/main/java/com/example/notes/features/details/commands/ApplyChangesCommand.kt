package com.example.notes.features.details.commands

import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.core.base.OnSetupToolbarCallback
import com.example.core.extension.view.setVisibility
import com.example.core.extension.view.startAnimation
import com.example.core.model.ToolbarSettings
import com.example.notes.R
import com.example.notes.common.Command
import com.example.notes.databinding.FragmentNoteDetailsBinding
import com.example.notes.features.details.presentation.NoteDetailsViewModel
import com.example.notes.models.NoteUi
import com.example.notes.utility.executeCommand

class ApplyChangesCommand(
    private val noteId: Int,
    private val noteUi: NoteUi?,
    private val navController: NavController,
    private val viewModel: NoteDetailsViewModel,
    private var binding: FragmentNoteDetailsBinding
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
            Toast.makeText(binding.root.context, "The note is empty", Toast.LENGTH_SHORT).show()
        } else {
            executeCommand(CreateNoteCommand(viewModel, binding))
            navController.popBackStack()
        }
    }

    private fun updateNote() {
        executeCommand(UpdateNoteCommand(noteUi!!, viewModel, binding))
        navController.popBackStack()
    }

    private fun checkEmptyNote() = binding.editTextTitle.text.toString().isEmpty() &&
            binding.editTextTextDescription.text.toString().isEmpty()
}
