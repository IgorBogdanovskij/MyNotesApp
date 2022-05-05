package com.example.notes.features.notes.recycler.notes

import android.view.View
import androidx.core.content.ContextCompat
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
            onNoteClickListener.onNoteLongClick(
                itemNotesBinding.root,
                itemByPositionCallback(adapterPosition)
            )
            return@setOnLongClickListener true
        }
    }

    fun bind(noteUi: NoteUi) {
        itemNotesBinding.textForAdapterItemTitle.text = noteUi.title
        itemNotesBinding.textForAdapterItemDescription.text = noteUi.description
        if (noteUi.colorBackground != 0) {
            itemNotesBinding.cardViewForNote.setCardBackgroundColor(
                ContextCompat.getColor(itemNotesBinding.root.context, noteUi.colorBackground)
            )
        }
        if (noteUi.group.isEmpty()) {
            itemNotesBinding.groupName.visibility = View.GONE
        } else {
            itemNotesBinding.groupName.visibility = View.VISIBLE
            itemNotesBinding.groupName.text = noteUi.group
        }
    }
}
