package com.example.notes.features.notes.ui

import android.util.Log
import android.view.View
import com.example.notes.databinding.FragmentNotesBinding
import com.example.notes.features.notes.presentation.State
import com.example.notes.features.notes.recycler.notes.NotesAdapter

class NotesUiHandler(
    private val fragmentNotesBinding: FragmentNotesBinding,
    private val notesAdapter: NotesAdapter
) {

    fun handle(state: State){
        when (state) {
            is State.Success -> {
                fragmentNotesBinding.emptyListItem.root.visibility = View.GONE
                notesAdapter.submitList(state.notes)
                Log.d("lol", "handle: NotesUiHandler ${state.notes}")
            }
            is State.Error -> {
                fragmentNotesBinding.emptyListItem.EmptyListTextView.text = state.errorText
                fragmentNotesBinding.emptyListItem.root.visibility = View.VISIBLE
            }
        }
    }
}
