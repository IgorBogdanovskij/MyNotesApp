package com.example.data.repository

import com.example.data.locale.dao.NoteDao
import com.example.domainn.entity.NoteEntity
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import java.util.*
import org.junit.Assert.*

class NotesRepositoryImpTest {

    @Test
    fun mip(){

    }

    @Test
    fun get_note_without_error() {

        val noteDao = mockk<NoteDao>{
            every { getNoteById(ID_NOTE) } returns Single.error(DataBaseError())
        }

        val notesRepositoryImp = NotesRepositoryImp(noteDao)

        assertEquals(noteEntity, notesRepositoryImp.getNoteById(ID_NOTE).blockingGet())
    }



    companion object {

        private const val ID_NOTE = 1

        val noteEntity = NoteEntity("", "", 0, 0, Date(), "").apply {
            id = ID_NOTE
        }
    }
}

class DataBaseError : Exception()