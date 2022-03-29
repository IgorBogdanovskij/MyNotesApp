package com.example.notes.di.domain

import com.example.domainn.interactor.NotesInteractor
import com.example.domainn.interactor.NotesInteractorImp
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [DomainModule.InnerDomainModule::class])
class DomainModule {

    @Module
    interface InnerDomainModule {

        @Binds
        @Singleton
        fun bindNotesInteractor(notesInteractorImp: NotesInteractorImp): NotesInteractor
    }
}