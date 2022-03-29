package com.example.domainn.repository

import com.example.domainn.entity.NoteEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface NotesRepository {
    fun getAllNotes(): Single<List<NoteEntity>>

    fun getAllNotesByGroup(nameGroup: String): Single<List<NoteEntity>>

    fun getAllNotesWithNameGroup(): Single<List<String>>

    fun getNoteById(id: Int): Single<NoteEntity>

    fun addNote(noteEntity: NoteEntity): Completable

    fun updateNote(noteEntity: NoteEntity): Completable

    fun deleteNote(id: Int): Completable
}