package com.example.notes.features.listNotes

import com.example.domainn.entity.NoteEntity
import com.example.domainn.interactor.NotesInteractor
import com.example.notes.features.notes.presentation.NotesViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single

import org.junit.Test
import java.util.*

class NotesViewModelTest {

    @ExperimentalStdlibApi
    @Test
    fun getAllNotes() {

        //Arrange
        val interactor = mockk<NotesInteractor>{
            every { getAllNotes() }.returns(Single.just(listNotes))
        }

        val viewModel = NotesViewModel(interactor)

        //Act
        viewModel.getAllNotes()

        //Assert
        verify {
            interactor.getAllNotes()
        }
    }

    companion object{

        @ExperimentalStdlibApi
        val listNotes = buildList<NoteEntity>(10){
            NoteEntity("","",0,0, Date(),"")
        }
    }
}