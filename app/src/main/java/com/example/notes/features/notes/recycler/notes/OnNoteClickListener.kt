package com.example.notes.features.notes.recycler.notes

import android.view.View
import com.example.notes.models.NoteUi

interface OnNoteClickListener {
    fun onNoteClick(noteUi: NoteUi)
    fun onNoteLongClick(view: View)
}
