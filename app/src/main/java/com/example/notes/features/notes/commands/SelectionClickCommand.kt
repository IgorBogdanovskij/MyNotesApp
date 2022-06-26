package com.example.notes.features.notes.commands

import android.util.Log
import android.widget.Toast
import com.example.core.base.OnSetupToolbarCallback
import com.example.core.extension.view.setVisibility
import com.example.notes.R
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
    }

    private fun runSelectionOperation() {
        val context = notesFragment.requireContext()
        with(selectionManager) {
            if (isAlreadyAdded(noteUi)) {
                unselect(noteUi)
                notesFragment.toolbarSettings = notesFragment.toolbarSettings.copy(
                    titleInSelectionMode = context.getString(
                        R.string.selected, selectionManager.getAllSelectedItems().size
                    )
                )
                onSetupToolbarCallback.onSetupToolbar(notesFragment.toolbarSettings)
                if (isEmpty()) {
                    setupItemsVisibility(true)
                    with(notesFragment) {
                        toolbarSettings = toolbarSettings.copy(isSelectionModeActive = false)
                        onSetupToolbarCallback.onSetupToolbar(toolbarSettings)
                        setupNavigationView()
                    }
                }
            } else {
                select(noteUi)
                notesFragment.toolbarSettings = notesFragment.toolbarSettings.copy(
                    titleInSelectionMode = context.getString(
                        R.string.selected, selectionManager.getAllSelectedItems().size
                    )
                )
                onSetupToolbarCallback.onSetupToolbar(notesFragment.toolbarSettings)
            }
        }
    }

    private fun onCancelButtonActions() {
        selectionManager.unselectAll()
        with(notesFragment) {
            toolbarSettings = notesFragment.toolbarSettings.copy(isSelectionModeActive = false)
            onSetupToolbarCallback.onSetupToolbar(toolbarSettings)
            setupItemsVisibility(true)
            setupNavigationView()
        }
    }

    private fun onDeleteNotesAction() {
        notesViewModel.deleteNotes(selectionManager.getAllSelectedItems())
    }

    private fun setupItemsVisibility(isVisible: Boolean) {
        with(binding) {
            createNewNote.setVisibility(isVisible)
            circleUnderCreateNewNote.setVisibility(isVisible)
        }
    }
}
