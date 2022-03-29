package com.example.notes.di

import android.content.Context
import com.example.notes.di.data.DataModule
import com.example.notes.di.domain.DomainModule
import com.example.notes.di.viewModel.ViewModelFactoryModule
import com.example.notes.features.notes_feature.di.NotesComponent
import com.example.notes.features.notedetails_feature.di.NoteDetailsComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, DomainModule::class, ViewModelFactoryModule::class])
interface AppComponent {

    fun plusNotesComponent(): NotesComponent

    fun plusNoteDetailsComponent(): NoteDetailsComponent

    @Component.Builder
    interface Builder {

        fun context(@BindsInstance context: Context): Builder

        fun create(): AppComponent
    }
}