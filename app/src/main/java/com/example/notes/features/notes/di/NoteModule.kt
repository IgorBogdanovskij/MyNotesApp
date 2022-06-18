package com.example.notes.features.notes.di

import androidx.lifecycle.ViewModel
import com.example.data.sharedPrefManager.SharedPreferencesManager
import com.example.data.sharedPrefManager.SharedPreferencesManagerImpl
import com.example.notes.di.viewModel.ViewModelKey
import com.example.notes.features.notes.presentation.NotesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [NoteModule.InnerNoteModule::class])
class NoteModule {

    @Module
    interface InnerNoteModule {

        @Binds
        @IntoMap
        @ViewModelKey(NotesViewModel::class)
        fun bindNotesViewModel(viewModel: NotesViewModel): ViewModel

        @Binds
        @NotesScope
        fun bindSharedPreferencesManager(sharedPreferencesManagerImpl: SharedPreferencesManagerImpl): SharedPreferencesManager
    }
}
