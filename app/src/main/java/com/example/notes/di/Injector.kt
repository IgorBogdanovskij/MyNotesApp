package com.example.notes.di

import android.content.Context
import com.example.notes.features.details.ui.NoteDetailsFragment
import com.example.notes.features.details.di.NoteDetailsComponent
import com.example.notes.features.edit.di.EditComponent
import com.example.notes.features.edit.ui.EditFragment
import com.example.notes.features.notes.di.NotesComponent
import com.example.notes.features.notes.ui.NotesFragment

object Injector {

    private lateinit var appComponent: AppComponent
    private var notesComponent: NotesComponent? = null
    private var noteDetailsComponent: NoteDetailsComponent? = null
    private var editComponent: EditComponent? = null

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

    fun inject(editFragment: EditFragment) {
        editComponent =
            appComponent.plusEditComponent().also { it.inject(editFragment) }
    }

    fun clearEditComponent(){
        editComponent = null
    }
}
