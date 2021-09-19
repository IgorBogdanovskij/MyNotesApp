package com.example.notes.data.repository

import com.example.notes.data.locale.dao.NoteDaoKotlin
import com.example.notes.data.locale.entity.NoteEntityKotlin
import com.example.notes.domain.repository.INotesRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class NotesRepositoryImp(
    val noteDao: NoteDaoKotlin
) : INotesRepository {
    override fun getAllNotes(): Single<List<NoteEntityKotlin>> {
        return noteDao.getAllNotes()
            .subscribeOn(Schedulers.io())
    }

    override fun getAllNotesByGroup(nameGroup: String): Single<List<NoteEntityKotlin>> {
        return noteDao.getAllNotesByGroup(nameGroup = nameGroup)
            .subscribeOn(Schedulers.io())
    }

    override fun getAllNotesWithNameGroup(): Single<List<String>> {
        return noteDao.getAllNotesWithNameGroup()
            .subscribeOn(Schedulers.io())
    }

    override fun getNoteById(id: Int): Single<NoteEntityKotlin> {
        return noteDao.getNoteById(id)
            .subscribeOn(Schedulers.io())
    }

    override fun addNote(noteEntityKotlin: NoteEntityKotlin): Completable {
        return noteDao.addNote(noteEntityKotlin)
            .subscribeOn(Schedulers.io())
    }

    override fun updateNote(noteEntityKotlin: NoteEntityKotlin): Completable {
        return noteDao.updateNote(noteEntityKotlin)
            .subscribeOn(Schedulers.io())
    }

    override fun deleteNote(noteEntityKotlin: NoteEntityKotlin): Completable {
        return noteDao.deleteNote(noteEntityKotlin)
            .subscribeOn(Schedulers.io())
    }
}