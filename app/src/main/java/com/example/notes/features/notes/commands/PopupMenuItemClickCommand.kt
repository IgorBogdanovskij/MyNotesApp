package com.example.notes.features.notes.commands

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.example.notes.R
import com.example.notes.common.Command
import com.example.notes.features.notes.presentation.NotesViewModel
import com.example.notes.features.notes.recycler.notes.NotesAdapter
import com.example.notes.features.notes.ui.NotesFragment
import com.example.notes.models.NoteUi
import com.example.notes.utility.executeCommand

class PopupMenuItemClickCommand(
    private val idMenuItem: Int,
    private val navController: NavController,
    private val notesViewModel: NotesViewModel,
    private val noteUi: NoteUi?,
    private val notesFragment: NotesFragment,
) : Command {

    override fun execute() {
        when (idMenuItem) {
            R.id.editPopupDrawer -> {
                navController.navigate(R.id.editFragment, bundleOf(NotesFragment.NOTE_ID to noteUi!!.id))
            }
            R.id.deletePopupDrawer -> {
                executeCommand(DeleteNoteCommand(noteUi!!, notesViewModel, notesFragment))
            }
        }
    }
}
