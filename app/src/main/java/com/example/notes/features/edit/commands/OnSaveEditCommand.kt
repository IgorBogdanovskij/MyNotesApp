package com.example.notes.features.edit.commands

import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import com.example.notes.common.Command
import com.example.notes.databinding.FragmentEditBinding
import com.example.notes.features.edit.presentation.EditViewModel
import com.example.notes.features.edit.ui.ColorPicker
import com.example.notes.mappers.mapToNoteEntity
import com.example.notes.models.NoteUi

class OnSaveEditCommand(
    private val navController: NavController,
    private val viewBinding: FragmentEditBinding,
    private val viewModel: EditViewModel,
    private val colorPicker: ColorPicker,
    private val noteUi: NoteUi
) : Command {

    // TODO: correct popBackStack with save new state 
    override fun execute() {
        val updatedNoteUi = noteUi.copy(colorBackground = colorPicker.getColor())
        viewModel.updateNote(mapToNoteEntity(updatedNoteUi))
        navController.popBackStack()
    }
}
