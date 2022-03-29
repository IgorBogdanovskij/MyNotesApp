package com.example.notes.features.notedetails_feature.di

import com.example.notes.features.notedetails_feature.NoteDetailsFragment
import dagger.Subcomponent

@Subcomponent(modules = [NoteDetailsModule::class])
interface NoteDetailsComponent {
    fun inject(noteDetailsFragment: NoteDetailsFragment)
}
