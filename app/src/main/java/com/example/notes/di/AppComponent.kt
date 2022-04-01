package com.example.notes.di

import android.content.Context
import com.example.notes.di.data.DataModule
import com.example.notes.di.viewModel.ViewModelModule
import com.example.notes.features.notedetails_feature.di.NoteDetailsComponent
import com.example.notes.features.notes.di.NotesComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, ViewModelModule::class])
interface AppComponent {

    fun plusNotesComponent(): NotesComponent
    fun plusNoteDetailsComponent(): NoteDetailsComponent

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun create(): AppComponent
    }
}
