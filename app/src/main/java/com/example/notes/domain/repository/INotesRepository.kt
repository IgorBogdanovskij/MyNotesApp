package com.example.notes.domain.repository

import com.example.notes.data.locale.entity.NoteEntityKotlin
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface INotesRepository {
    fun getAllNotes(): Single<List<NoteEntityKotlin>>

    fun getAllNotesByGroup(nameGroup: String): Single<List<NoteEntityKotlin>>

    fun getAllNotesWithNameGroup(): Single<List<String>>

    fun getNoteById(id: Int): Single<NoteEntityKotlin>

    fun addNote(noteEntityKotlin: NoteEntityKotlin): Completable

    fun updateNote(noteEntityKotlin: NoteEntityKotlin): Completable

    fun deleteNote(noteEntityKotlin: NoteEntityKotlin): Completable
}