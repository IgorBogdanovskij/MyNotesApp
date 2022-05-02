package com.example.notes.features.edit.di

import androidx.lifecycle.ViewModel
import com.example.domainn.interactor.NotesInteractor
import com.example.domainn.interactor.NotesInteractorImp
import com.example.notes.di.viewModel.ViewModelKey
import com.example.notes.features.edit.presentation.EditViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [EditModule.InnerEditModule::class])
class EditModule {

    @Module
    interface InnerEditModule {

        @Binds
        @IntoMap
        @ViewModelKey(EditViewModel::class)
        fun bindNotesViewModel(editViewModel: EditViewModel): ViewModel
    }
}
