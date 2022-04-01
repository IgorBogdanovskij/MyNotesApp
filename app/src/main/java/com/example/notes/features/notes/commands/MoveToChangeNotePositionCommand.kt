package com.example.notes.features.notes.commands

import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.common.Command
import com.example.notes.features.notes.presentation.NotesViewModel
import com.example.notes.features.notes.recycler.notes.NotesAdapter
import com.example.notes.mappers.mapToNoteEntity

class MoveToChangeNotePositionCommand(
    private val viewHolder: RecyclerView.ViewHolder,
    private val target: RecyclerView.ViewHolder,
    private val popupMenu: PopupMenu?,
    private val notesAdapter: NotesAdapter,
    private val notesViewModel: NotesViewModel
) : Command {
    override fun execute() {
        popupMenu?.dismiss()

        val mFromPosition = viewHolder.adapterPosition
        val mToPosition = target.adapterPosition

        val fromNote = notesAdapter.getNotes().removeAt(mFromPosition);
        notesAdapter.getNotes().add(mToPosition, fromNote);

        notesViewModel.moveNotes(
            mapToNoteEntity(notesAdapter.getNotes()[mFromPosition]),
            mapToNoteEntity(notesAdapter.getNotes()[mToPosition])
        )
        notesAdapter.notifyItemMoved(mFromPosition, mToPosition);
    }
}