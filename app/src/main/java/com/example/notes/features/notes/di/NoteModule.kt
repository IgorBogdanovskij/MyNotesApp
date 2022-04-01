package com.example.notes.features.notes.di

import androidx.lifecycle.ViewModel
import com.example.domainn.interactor.NotesInteractor
import com.example.domainn.interactor.NotesInteractorImp
import com.example.notes.di.viewModel.ViewModelKey
import com.example.notes.features.notes.presentation.NotesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

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
        fun bindNotesInteractor(notesInteractorImp: NotesInteractorImp): NotesInteractor
    }
}
