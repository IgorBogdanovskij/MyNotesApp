package com.example.notes.features.details.di

import com.example.notes.features.details.ui.NoteDetailsFragment
import dagger.Subcomponent

@NoteDetailsScope
@Subcomponent(modules = [NoteDetailsModule::class])
interface NoteDetailsComponent {
    fun inject(noteDetailsFragment: NoteDetailsFragment)
}
