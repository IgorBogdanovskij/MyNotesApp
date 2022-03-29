package com.example.data.repository

import com.example.data.locale.dao.NoteDao
import com.example.domainn.entity.NoteEntity
import com.example.domainn.repository.NotesRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
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
            .subscribeOn(Schedulers.io())
    }

    override fun getNoteById(id: Int): Single<NoteEntity> {
        return noteDao.getNoteById(id)
            .subscribeOn(Schedulers.io())
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
}