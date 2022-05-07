package com.example.notes.features.notes.commands

import android.view.View
import androidx.core.view.allViews
import com.example.notes.R
import com.example.notes.common.Command
import com.example.notes.databinding.FragmentNotesBinding
import com.example.notes.features.notes.model.NotesFilterType
import com.example.notes.features.notes.presentation.NotesViewModel

class ShowNotesByFilterCommand(
    private val notesBinding: FragmentNotesBinding,
    private val notesViewModel: NotesViewModel,
    private val filterType: NotesFilterType,
    private val nameGroup: String = "",
    private val view: View,
) : Command {

    override fun execute() {
        when (filterType) {
            NotesFilterType.ALLNOTES -> {
                notesBinding.includeDrawer.root.allViews.forEach {
                    if (view == it) {
                        view.setBackgroundResource(R.drawable.select_group_background)
                    }
                }.also { notesViewModel.getAllNotes() }

                repeat(notesBinding.includeDrawer.drawerRecyclerView.childCount) { position ->
                    notesBinding.includeDrawer.drawerRecyclerView.getChildAt(position)
                        .setBackgroundResource(0)
                }
            }
            NotesFilterType.NOTESBYGROUP -> {
                repeat(notesBinding.includeDrawer.drawerRecyclerView.childCount) { position ->
                    val viewHolder =
                        notesBinding.includeDrawer.drawerRecyclerView.getChildAt(position)
                    if (viewHolder == view) {
                        view.setBackgroundResource(R.drawable.select_group_background)
                    } else {
                        viewHolder.setBackgroundColor(0)
                    }
                }.also {
                    notesViewModel.getAllNewsByGroup(nameGroup)
                    notesBinding.includeDrawer.linearAllNote.setBackgroundResource(0)
                }
            }
        }
    }
}
