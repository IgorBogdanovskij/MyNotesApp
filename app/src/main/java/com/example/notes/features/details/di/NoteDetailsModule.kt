package com.example.notes.features.details.di

import androidx.lifecycle.ViewModel
import com.example.domainn.interactor.NotesInteractor
import com.example.domainn.interactor.NotesInteractorImp
import com.example.notes.di.viewModel.ViewModelKey
import com.example.notes.features.details.presentation.NoteDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [NoteDetailsModule.InnerNoteDetailsModule::class])
class NoteDetailsModule {

    @Module
    interface InnerNoteDetailsModule {
        @Binds
        @IntoMap
        @ViewModelKey(NoteDetailsViewModel::class)
        fun bindNoteDetailsViewModel(viewModel: NoteDetailsViewModel): ViewModel

        @Binds
        @NoteDetailsScope
        fun bindNotesInteractor(notesInteractorImp: NotesInteractorImp): NotesInteractor
    }
}
