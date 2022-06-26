package com.example.notes.features.notes.recycler.notes

import android.content.res.Configuration
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.ContextCompat.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.databinding.NoteItemBinding
import com.example.notes.models.NoteUi
import java.lang.IllegalStateException

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
        val cardBackgroundColor =
            when (itemNotesBinding.root.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
                Configuration.UI_MODE_NIGHT_YES -> R.color.cardview_dark_background
                Configuration.UI_MODE_NIGHT_NO -> R.color.cardview_light_background
                Configuration.UI_MODE_NIGHT_UNDEFINED -> R.color.blue
                else -> throw IllegalStateException()
            }
        with(itemNotesBinding) {
            val context = root.context
            textForAdapterItemTitle.text = noteUi.title
            textForAdapterItemDescription.text = noteUi.description
            if (noteUi.colorBackground != 0) {
                cardViewNote.setCardBackgroundColor(getColor(context, noteUi.colorBackground))
            } else {
                cardViewNote.setCardBackgroundColor(getColor(context, cardBackgroundColor))
            }
            if (noteUi.group.isEmpty()) {
                groupName.visibility = View.GONE
            } else {
                groupName.visibility = View.VISIBLE
                groupName.text = noteUi.group
            }
            if (noteUi.isFocusable) {
                noteContainer.background =
                    getDrawable(context, R.drawable.selection_note_item_background)
            } else {
                if (noteUi.colorBackground != 0) {
                    noteContainer.setBackgroundColor(getColor(context, noteUi.colorBackground))
                } else {
                    noteContainer.setBackgroundColor(getColor(context, cardBackgroundColor))
                }
            }
        }
    }
}
