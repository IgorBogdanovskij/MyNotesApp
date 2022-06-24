package com.example.notes.features.notes.commands

import android.opengl.Visibility
import android.view.View
import android.widget.Toast
import com.example.core.base.OnSetupToolbarCallback
import com.example.core.extension.view.setVisibility
import com.example.core.model.ToolbarSettings
import com.example.notes.common.Command
import com.example.notes.databinding.FragmentNotesBinding
import com.example.notes.features.notes.presentation.NotesViewModel
import com.example.notes.features.notes.ui.NotesFragment
import com.example.notes.features.notes.ui.SelectionManager
import com.example.notes.models.NoteUi

class SelectionClickCommand(
    private val noteUi: NoteUi,
    private val binding: FragmentNotesBinding,
    private val notesViewModel: NotesViewModel,
    private val selectionManager: SelectionManager,
    private val notesFragment: NotesFragment,
    private val onSetupToolbarCallback: OnSetupToolbarCallback
) : Command {

    override fun execute() {
        notesFragment.noteUi = noteUi
        notesFragment.toolbarSettings = notesFragment.toolbarSettings.copy(
            isSelectionModeActive = true,
            onCancelButtonListener = this::onCancelButtonActions,
            onDeleteIconListener = this::onDeleteNotesAction
        )
        setupItemsVisibility(false)
        runSelectionOperation()
        onSetupToolbarCallback.onSetupToolbar(notesFragment.toolbarSettings)
    }

    private fun setupItemsVisibility(isVisible: Boolean) {
        with(binding) {
            createNewNote.setVisibility(isVisible)
            circleUnderCreateNewNote.setVisibility(isVisible)
        }
    }

    private fun onCancelButtonActions() {
        selectionManager.unselectAll()
        notesFragment.toolbarSettings =
            notesFragment.toolbarSettings.copy(isSelectionModeActive = false)
        onSetupToolbarCallback.onSetupToolbar(notesFragment.toolbarSettings)
        setupItemsVisibility(true)
    }

    private fun onDeleteNotesAction() {
        notesViewModel.deleteNotes(selectionManager.getAllSelectedItems())
    }

    private fun runSelectionOperation() {
        with(selectionManager) {
            if (isAlreadyAdded(noteUi)) {
                Toast.makeText(notesFragment.requireContext(), "${isAlreadyAdded(noteUi)}", Toast.LENGTH_SHORT).show()
                unselect(noteUi)
                if (isEmpty()) {
                    setupItemsVisibility(true)
                    notesFragment.toolbarSettings =
                        notesFragment.toolbarSettings.copy(isSelectionModeActive = false)
                }
            } else {
                select(noteUi)
            }
        }
    }
}
