package com.example.notes.features.notes.commands

import android.view.View
import com.example.notes.R
import com.example.notes.common.Command
import com.example.notes.databinding.FragmentNotesBinding
import com.example.notes.features.notes.presentation.NotesViewModel

class ShowNotesByGroupCommand(
    private val notesBinding: FragmentNotesBinding,
    private val notesViewModel: NotesViewModel,
    private val nameGroup: String,
    private val view: View,
) : Command {

    override fun execute() {
        repeat(notesBinding.includeDrawer.drawerRecyclerView.childCount) { position ->
            if (notesBinding.includeDrawer.drawerRecyclerView.getChildAt(position) == view) {
                view.setBackgroundResource(R.drawable.select_group_background)
            } else {
                notesBinding.includeDrawer.drawerRecyclerView.getChildAt(position)
                    .setBackgroundColor(0)
            }
        }.also {
            notesViewModel.getAllNewsByGroup(nameGroup)
            notesBinding.includeDrawer.linearAllNote.setBackgroundResource(0)
        }
    }
}
