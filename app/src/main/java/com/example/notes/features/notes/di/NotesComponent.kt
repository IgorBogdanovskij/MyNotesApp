package com.example.notes.features.notes.di

import com.example.notes.features.notes.ui.NotesFragment
import dagger.Subcomponent

@NotesScope
@Subcomponent(modules = [NoteModule::class])
interface NotesComponent {
    fun inject(notesFragment: NotesFragment)
}
