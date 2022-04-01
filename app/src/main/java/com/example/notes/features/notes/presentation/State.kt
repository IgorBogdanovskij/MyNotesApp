package com.example.notes.features.notes.presentation

import com.example.notes.models.NoteUi

sealed class State{
    class Success(val notes: List<NoteUi>): State()
    class Error(val errorText: String): State()
}
