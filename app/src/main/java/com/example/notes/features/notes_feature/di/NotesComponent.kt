package com.example.notes.features.notes_feature.di

import com.example.notes.features.notes_feature.NotesFragment
import dagger.Subcomponent

@Subcomponent(modules = [NoteModule::class])
interface NotesComponent {
    fun inject(notesFragment: NotesFragment)
}
