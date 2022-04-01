package com.example.notes.features.notes.commands

import android.view.View
import androidx.core.view.allViews
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.common.Command
import com.example.notes.databinding.FragmentNotesBinding
import com.example.notes.features.notes.presentation.NotesViewModel

class ShowAllNotesCommand(
    private val fragmentNotesBinding: FragmentNotesBinding,
    private val notesViewModel: NotesViewModel,
    private val groupRecycleView: RecyclerView,
    private val view: View
) : Command {

    override fun execute() {
        fragmentNotesBinding.includeDrawer.root.allViews.forEach {
            if (view == it) {
                view.setBackgroundResource(R.drawable.select_item)
            } else {
                it.invalidate()
            }
        }.also { notesViewModel.getAllNotes() }

        repeat(groupRecycleView.childCount){ position ->
            groupRecycleView.getChildAt(position).setBackgroundResource(0)
        }
    }
}
