package com.example.notes.features.notes.commands

import com.example.notes.common.Command
import com.example.notes.features.notes.presentation.NotesViewModel
import com.example.notes.features.notes.recycler.notes.NotesAdapter
import com.example.notes.mappers.mapToNoteEntity

class SaveSwapToDataBaseCommand(
    private val notesViewModel: NotesViewModel,
    private val fromAdapterPosition: Int,
    private val toAdapterPosition: Int,
    private val notesAdapter: NotesAdapter
) : Command {

    override fun execute() {
        if (fromAdapterPosition != -1 && toAdapterPosition != -1) {
            val fromNote = notesAdapter.currentList[fromAdapterPosition]
            val toNote = notesAdapter.currentList[toAdapterPosition]
            val fromNoteModified = fromNote.copy(sortDate = toNote.sortDate).apply {
                id = fromNote.id
            }
            val toNoteModified = toNote.copy(sortDate = fromNote.sortDate).apply {
                id = toNote.id
            }
            with(notesViewModel) {
                updateNote(mapToNoteEntity(fromNoteModified))
                updateNote(mapToNoteEntity(toNoteModified))
            }
        }
    }
}
