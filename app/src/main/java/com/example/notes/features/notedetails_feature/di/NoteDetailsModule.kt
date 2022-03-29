package com.example.notes.features.notedetails_feature.di

import androidx.lifecycle.ViewModel
import com.example.notes.di.viewModel.ViewModelFactoryModule
import com.example.notes.di.viewModel.ViewModelKey
import com.example.notes.features.notedetails_feature.NoteDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelFactoryModule::class])
abstract class NoteDetailsModule {

    @Binds
    @IntoMap
    @ViewModelKey(NoteDetailsViewModel::class)
    abstract fun bindNoteWriteViewModel(viewModel: NoteDetailsViewModel): ViewModel
}