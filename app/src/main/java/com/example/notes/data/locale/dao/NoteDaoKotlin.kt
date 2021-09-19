package com.example.notes.data.locale.dao

import androidx.room.*
import com.example.notes.data.locale.entity.NoteEntityKotlin
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface NoteDaoKotlin {

    @Query("SELECT * FROM NoteEntityKotlin ORDER BY data DESC")
    fun getAllNotes(): Single<List<NoteEntityKotlin>>

    @Query("SELECT * FROM NoteEntityKotlin WHERE nameGroup == :nameGroup ORDER BY data DESC")
    fun getAllNotesByGroup(nameGroup: String): Single<List<NoteEntityKotlin>>

    @Query("SELECT DISTINCT nameGroup FROM NoteEntityKotlin WHERE nameGroup !='' ORDER BY data")
    fun getAllNotesWithNameGroup(): Single<List<String>>

    @Query("SELECT * FROM NoteEntityKotlin WHERE id == :id")
    fun getNoteById(id: Int): Single<NoteEntityKotlin>

    @Insert
    fun addNote(noteEntityKotlin: NoteEntityKotlin): Completable

    @Update
    fun updateNote(noteEntityKotlin: NoteEntityKotlin): Completable

    @Delete
    fun deleteNote(noteEntityKotlin: NoteEntityKotlin): Completable
}