package com.example.notes.features.notes.recycler.notes

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.NoteItemBinding
import com.example.notes.models.NoteUi

class NotesViewHolder(
    private val itemNotesBinding: NoteItemBinding,
    private val onNoteClickListener: OnNoteClickListener,
    private val itemByPositionCallback: (Int) -> NoteUi,
) : RecyclerView.ViewHolder(itemNotesBinding.root) {

    init {
        itemNotesBinding.root.setOnClickListener {
            onNoteClickListener.onNoteClick(itemByPositionCallback(adapterPosition))
        }
        itemNotesBinding.root.setOnLongClickListener {
            onNoteClickListener.onNoteLongClick(itemNotesBinding.root)
            return@setOnLongClickListener true
        }
    }

    fun bind(noteEntity: NoteUi) {
        itemNotesBinding.textForAdapterItemTitle.text = noteEntity.title
        itemNotesBinding.textForAdapterItemDescription.text = noteEntity.description
        if (noteEntity.nameGroup.isEmpty()) {
            itemNotesBinding.groupName.visibility = View.GONE
        } else {
            itemNotesBinding.groupName.visibility = View.VISIBLE
            itemNotesBinding.groupName.text = noteEntity.nameGroup
        }
    }
}
