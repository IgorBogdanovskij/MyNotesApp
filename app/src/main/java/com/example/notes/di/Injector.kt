package com.example.notes.di

import android.content.Context
import com.example.notes.features.notedetails_feature.NoteDetailsFragment
import com.example.notes.features.notedetails_feature.di.NoteDetailsComponent
import com.example.notes.features.notes.di.NotesComponent
import com.example.notes.features.notes.ui.NotesFragment

object Injector {

    private lateinit var appComponent: AppComponent
    private var notesComponent: NotesComponent? = null
    private var noteDetailsComponent: NoteDetailsComponent? = null

    fun initAppComponent(context: Context) {
        appComponent = DaggerAppComponent.builder().context(context).create()
    }

    fun inject(notesFragment: NotesFragment) {
        notesComponent = appComponent.plusNotesComponent().also { it.inject(notesFragment) }
    }

    fun clearNotesComponent() {
        notesComponent = null
    }

    fun inject(noteDetailsFragment: NoteDetailsFragment) {
        noteDetailsComponent =
            appComponent.plusNoteDetailsComponent().also { it.inject(noteDetailsFragment) }
    }

    fun clearNoteDetailsComponent(){
        noteDetailsComponent = null
    }
}
