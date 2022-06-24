package com.example.data.repository

import android.util.Log
import com.example.data.local.dao.NoteDao
import com.example.domainn.entity.NoteEntity
import com.example.domainn.repository.NotesRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NotesRepositoryImp @Inject constructor(
    val noteDao: NoteDao
) : NotesRepository {
    override fun getAllNotes(): Single<List<NoteEntity>> {
        return noteDao.getAllNotes()
            .subscribeOn(Schedulers.io())
    }

    override fun getAllNotesByGroup(nameGroup: String): Single<List<NoteEntity>> {
        return noteDao.getAllNotesByGroup(nameGroup = nameGroup)
            .subscribeOn(Schedulers.io())
    }

    override fun getAllNotesWithNameGroup(): Single<List<String>> {
        return noteDao.getAllNotesWithNameGroup()
            .delay(200, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
    }

    override fun getNoteById(id: Int): Single<NoteEntity> {
        return noteDao.getNoteById(id)
            .subscribeOn(Schedulers.io())
    }

    override fun getNoteByIdFlow(id: Int): Flow<NoteEntity> {
        return noteDao.getNoteByIdFlow(id).flowOn(Dispatchers.IO)
    }

    override fun addNote(noteEntity: NoteEntity): Completable {
        return noteDao.addNote(noteEntity)
            .subscribeOn(Schedulers.io())
    }

    override fun updateNote(noteEntity: NoteEntity): Completable {
        return noteDao.updateNote(noteEntity)
            .subscribeOn(Schedulers.io())
    }

    override fun deleteNote(id: Int): Completable {
        return noteDao.deleteNote(id)
            .subscribeOn(Schedulers.io())
    }

    override fun deleteNotes(notes: List<NoteEntity>): Completable {
        return noteDao.deleteNotes(notes)
            .subscribeOn(Schedulers.io())
    }
}