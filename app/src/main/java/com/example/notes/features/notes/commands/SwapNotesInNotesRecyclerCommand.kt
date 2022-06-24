package com.example.notes.features.notes.commands

import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.common.Command
import com.example.notes.features.notes.recycler.notes.NotesAdapter
import java.util.*

class SwapNotesInNotesRecyclerCommand(
    private val viewHolder: RecyclerView.ViewHolder,
    private val target: RecyclerView.ViewHolder,
    private val notesAdapter: NotesAdapter
) : Command {

    override fun execute() {
        val modifiedList = notesAdapter.currentList.toMutableList()
        Collections.swap(modifiedList, viewHolder.adapterPosition, target.adapterPosition)
        notesAdapter.submitList(modifiedList)
    }
}
