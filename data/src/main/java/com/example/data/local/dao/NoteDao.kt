package com.example.data.local.dao

import androidx.room.*
import com.example.domainn.entity.NoteEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes_table ORDER BY sortDate DESC")
    fun getAllNotes(): Single<List<NoteEntity>>

    @Query("SELECT * FROM notes_table WHERE nameGroup == :nameGroup ORDER BY createDate DESC")
    fun getAllNotesByGroup(nameGroup: String): Single<List<NoteEntity>>

    @Query("SELECT DISTINCT nameGroup FROM notes_table WHERE nameGroup !='' ORDER BY createDate")
    fun getAllNotesWithNameGroup(): Single<List<String>>

    @Query("SELECT * FROM notes_table WHERE id == :id")
    fun getNoteById(id: Int): Single<NoteEntity>

    @Query("SELECT * FROM notes_table WHERE id == :id")
    fun getNoteByIdFlow(id: Int): Flow<NoteEntity>

    @Insert
    fun addNote(noteEntity: NoteEntity): Completable

    @Update
    fun updateNote(noteEntity: NoteEntity): Completable

    @Query("DELETE FROM notes_table WHERE id = :id")
    fun deleteNote(id: Int): Completable

    @Delete
    fun deleteNotes(notes: List<NoteEntity>): Completable
}
