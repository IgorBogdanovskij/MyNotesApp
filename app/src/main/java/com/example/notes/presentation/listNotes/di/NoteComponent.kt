package com.example.notes.presentation.listNotes.di

import com.example.notes.presentation.listNotes.ListNotesFragmentKotlin
import com.example.notes.presentation.writeNote.di.NoteWriteModule
import com.example.notes.presentation.writeNote.di.NoteWriteComponent
import dagger.Subcomponent

@Subcomponent(modules = [NoteModule::class])
interface NoteComponent {

    fun inject(listNotesFragmentKotlin: ListNotesFragmentKotlin)

    @Subcomponent.Factory
    interface Factory {
        fun create(): NoteComponent
    }
}




