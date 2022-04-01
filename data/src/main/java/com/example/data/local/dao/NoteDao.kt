package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.domainn.entity.NoteEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes_table ORDER BY data DESC")
    fun getAllNotes(): Single<List<NoteEntity>>

    @Query("SELECT * FROM notes_table WHERE nameGroup == :nameGroup ORDER BY data DESC")
    fun getAllNotesByGroup(nameGroup: String): Single<List<NoteEntity>>

    @Query("SELECT DISTINCT nameGroup FROM notes_table WHERE nameGroup !='' ORDER BY data")
    fun getAllNotesWithNameGroup(): Single<List<String>>

    @Query("SELECT * FROM notes_table WHERE id == :id")
    fun getNoteById(id: Int): Single<NoteEntity>

    @Insert
    fun addNote(noteEntity: NoteEntity): Completable

    @Update
    fun updateNote(noteEntity: NoteEntity): Completable

    @Query("DELETE FROM notes_table WHERE id = :id")
    fun deleteNote(id: Int): Completable
}