package com.example.notes.features.notes_feature.state

import com.example.notes.models.NoteUI

sealed class State{
    class Success(val listNotes: List<NoteUI>): State()
    class Error(val someError: String): State()
}
