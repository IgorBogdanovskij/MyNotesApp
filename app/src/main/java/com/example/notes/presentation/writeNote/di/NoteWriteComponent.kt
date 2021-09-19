package com.example.notes.presentation.writeNote.di

import com.example.notes.presentation.listNotes.ListNotesFragmentKotlin
import com.example.notes.presentation.writeNote.NotesWriteFragmentKotlin
import dagger.Subcomponent

@Subcomponent(modules = [NoteWriteModule::class])
interface NoteWriteComponent {

    fun inject(write: NotesWriteFragmentKotlin)

    @Subcomponent.Factory
    interface Factory {
        fun create(): NoteWriteComponent
    }
}




