package com.example.notes.presentation.listNotes.di

import androidx.lifecycle.ViewModel
import com.example.notes.di.viewModel.ViewModelBuilderModule
import com.example.notes.di.viewModel.ViewModelKey
import com.example.notes.presentation.listNotes.NotesViewModelKotlin
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelBuilderModule::class])
abstract class NoteModule {

    @Binds
    @IntoMap
    @ViewModelKey(NotesViewModelKotlin::class)
    abstract fun bindNoteViewModel(viewModel: NotesViewModelKotlin): ViewModel
}