package com.example.notes.features.notes.commands

import com.example.notes.R
import com.example.notes.common.Command
import com.example.notes.databinding.FragmentNotesBinding
import com.example.notes.databinding.GroupItemBinding
import com.example.notes.features.notes.presentation.NotesViewModel

class ShowNotesByGroupCommand(
    private val notesBinding: FragmentNotesBinding,
    private val groupItemBinding: GroupItemBinding,
    private val notesViewModel: NotesViewModel,
    private val nameGroup: String
) : Command {

    override fun execute() {
        groupItemBinding.root.setBackgroundResource(R.drawable.select_item)
        notesViewModel.getAllNewsByGroup(nameGroup)
        notesBinding.includeDrawer.linearAllNote.setBackgroundResource(0)
    }
}
