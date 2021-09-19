package com.example.notes.di.domain

import com.example.notes.domain.interactor.INotesInteractor
import com.example.notes.domain.interactor.NotesInteractorImp
import com.example.notes.domain.repository.INotesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideNotesInteractor(repository: INotesRepository): INotesInteractor {
        return NotesInteractorImp(repository)
    }
}