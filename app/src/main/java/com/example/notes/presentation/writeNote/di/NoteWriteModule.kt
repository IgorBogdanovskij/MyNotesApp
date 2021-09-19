package com.example.notes.presentation.writeNote.di

import androidx.lifecycle.ViewModel
import com.example.notes.di.viewModel.ViewModelBuilderModule
import com.example.notes.di.viewModel.ViewModelKey
import com.example.notes.presentation.writeNote.NotesWriteViewModelKotlin
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelBuilderModule::class])
abstract class NoteWriteModule {

    @Binds
    @IntoMap
    @ViewModelKey(NotesWriteViewModelKotlin::class)
    abstract fun bindNoteWriteViewModel(viewModel: NotesWriteViewModelKotlin): ViewModel
}