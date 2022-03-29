package com.example.domainn.interactor

import com.example.domainn.entity.NoteEntity
import com.example.domainn.repository.NotesRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface NotesInteractor {
    fun getAllNotes(): Single<List<NoteEntity>>

    fun getAllNotesByGroup(nameGroup: String): Single<List<NoteEntity>>

    fun getAllNotesWithNameGroup(): Single<List<String>>

    fun getNoteById(id: Int): Single<NoteEntity>

    fun addNote(noteEntity: NoteEntity): Completable

    fun updateNote(noteEntity: NoteEntity): Completable

    fun deleteNote(id: Int): Completable
}

class NotesInteractorImp @Inject constructor(private val repository: NotesRepository) :
    NotesInteractor {

    override fun getAllNotes(): Single<List<NoteEntity>> {
        return repository.getAllNotes()
    }

    override fun getAllNotesByGroup(nameGroup: String): Single<List<NoteEntity>> {
        return repository.getAllNotesByGroup(nameGroup = nameGroup)
    }

    override fun getAllNotesWithNameGroup(): Single<List<String>> {
        return repository.getAllNotesWithNameGroup()
    }

    override fun getNoteById(id: Int): Single<NoteEntity> {
        return repository.getNoteById(id)
    }

    override fun addNote(noteEntity: NoteEntity): Completable {
        return repository.addNote(noteEntity)
    }

    override fun updateNote(noteEntity: NoteEntity): Completable {
        return repository.updateNote(noteEntity)
    }

    override fun deleteNote(id: Int): Completable {
        return repository.deleteNote(id)
    }
}