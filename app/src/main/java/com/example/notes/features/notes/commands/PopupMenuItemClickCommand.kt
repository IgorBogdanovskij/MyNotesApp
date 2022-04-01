package com.example.notes.features.notes.commands

import androidx.navigation.NavController
import com.example.notes.R
import com.example.notes.common.Command
import com.example.notes.features.notes.presentation.NotesViewModel
import java.lang.IllegalArgumentException

class PopupMenuItemClickCommand(
    private val idItem: Int,
    private val navController: NavController,
    private val notesViewModel: NotesViewModel,
    private val idNote: Int?,
) : Command {
    override fun execute() {
        when (idItem) {
            R.id.editPopupDrawer -> {
                navController.navigate(R.id.action_listNotesFragment_to_editFragmentKotlin)
            }
            R.id.deletePopupDrawer -> {
                notesViewModel.deleteNote(
                    id = idNote ?: throw IllegalArgumentException("There is not such note")
                )
            }
        }
    }
}