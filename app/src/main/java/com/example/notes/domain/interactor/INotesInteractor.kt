package com.example.notes.domain.interactor

import com.example.notes.data.locale.entity.NoteEntityKotlin
import com.example.notes.domain.repository.INotesRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface INotesInteractor {
    fun getAllNotes(): Single<List<NoteEntityKotlin>>

    fun getAllNotesByGroup(nameGroup: String): Single<List<NoteEntityKotlin>>

    fun getAllNotesWithNameGroup(): Single<List<String>>

    fun getNoteById(id: Int): Single<NoteEntityKotlin>

    fun addNote(noteEntityKotlin: NoteEntityKotlin): Completable

    fun updateNote(noteEntityKotlin: NoteEntityKotlin): Completable

    fun deleteNote(noteEntityKotlin: NoteEntityKotlin): Completable
}

class NotesInteractorImp(private val repository: INotesRepository) : INotesInteractor {

    override fun getAllNotes(): Single<List<NoteEntityKotlin>> {
        return repository.getAllNotes()
    }

    override fun getAllNotesByGroup(nameGroup: String): Single<List<NoteEntityKotlin>> {
        return repository.getAllNotesByGroup(nameGroup = nameGroup)
    }

    override fun getAllNotesWithNameGroup(): Single<List<String>> {
        return repository.getAllNotesWithNameGroup()
    }

    override fun getNoteById(id: Int): Single<NoteEntityKotlin> {
        return repository.getNoteById(id)
    }

    override fun addNote(noteEntityKotlin: NoteEntityKotlin): Completable {
        return repository.addNote(noteEntityKotlin)
    }

    override fun updateNote(noteEntityKotlin: NoteEntityKotlin): Completable {
        return repository.updateNote(noteEntityKotlin)
    }

    override fun deleteNote(noteEntityKotlin: NoteEntityKotlin): Completable {
        return repository.deleteNote(noteEntityKotlin)
    }
}