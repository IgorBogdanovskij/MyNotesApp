package com.example.notes.features.notes.recycler.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.notes.databinding.NoteItemBinding
import com.example.notes.models.NoteUi

class NotesAdapter(
    private val onNoteClickListener: OnNoteClickListener,
) : ListAdapter<NoteUi, NotesViewHolder>(object : DiffUtil.ItemCallback<NoteUi>() {

    override fun areItemsTheSame(oldItem: NoteUi, newItem: NoteUi) = oldItem == newItem

    override fun areContentsTheSame(oldItem: NoteUi, newItem: NoteUi): Boolean {
        return when {
            oldItem.id != newItem.id -> return false
            oldItem.title != newItem.title -> return false
            oldItem.description != newItem.description -> return false
            oldItem.createDate != newItem.createDate -> return false
            oldItem.sortDate != newItem.sortDate -> return false
            else -> true
        }
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onNoteClickListener,
            this::getItem,
        )
    }

    override fun onBindViewHolder(holderNotes: NotesViewHolder, position: Int) {
        holderNotes.bind(getItem(position))
    }

    fun getNoteByPosition(position: Int): NoteUi = getItem(position)
}
