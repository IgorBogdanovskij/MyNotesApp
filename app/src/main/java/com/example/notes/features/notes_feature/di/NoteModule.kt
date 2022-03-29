package com.example.notes.features.notes_feature.di

import androidx.lifecycle.ViewModel
import com.example.notes.di.viewModel.ViewModelFactoryModule
import com.example.notes.di.viewModel.ViewModelKey
import com.example.notes.features.notes_feature.NotesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class NoteModule {

    @Binds
    @IntoMap
    @ViewModelKey(NotesViewModel::class)
    abstract fun bindNoteViewModel(viewModel: NotesViewModel): ViewModel
}
