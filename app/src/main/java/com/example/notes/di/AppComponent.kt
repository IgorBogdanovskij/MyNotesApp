package com.example.notes.di

import android.content.Context
import com.example.notes.di.data.DataModule
import com.example.notes.di.domain.DomainModule
import com.example.notes.presentation.listNotes.di.NoteComponent
import com.example.notes.presentation.writeNote.di.NoteWriteComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, DomainModule::class])
interface AppComponent {

    fun noteComponent(): NoteComponent.Factory
    fun noteWriteComponent(): NoteWriteComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}