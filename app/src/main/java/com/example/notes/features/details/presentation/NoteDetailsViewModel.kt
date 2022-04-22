package com.example.notes.features.details.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domainn.entity.NoteEntity
import com.example.domainn.interactor.NotesInteractor
import com.example.notes.mappers.mapItemToNoteUI
import com.example.notes.models.NoteUi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class NoteDetailsViewModel @Inject constructor(
    private val interactor: NotesInteractor
) : ViewModel() {

    private var _noteUi: MutableLiveData<NoteUi> = MutableLiveData()
    val noteUi: LiveData<NoteUi>
        get() = _noteUi

    private var _allNameOfGroups: MutableLiveData<List<String>> = MutableLiveData()
    val allNameOfGroups: LiveData<List<String>>
        get() = _allNameOfGroups

    fun getNoteById(id: Int) {
        interactor.getNoteById(id)
            .map(::mapItemToNoteUI)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _noteUi.value = it },
                { Log.d("lo", "getNoteById: ${it.message}") })
    }

    fun addNote(noteEntity: NoteEntity) {
        interactor
            .addNote(noteEntity)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun updateNote(noteEntity: NoteEntity) {
        interactor
            .updateNote(noteEntity)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun getAllNameOfGroups() {
        interactor.getAllNotesWithNameGroup()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _allNameOfGroups.value = it
            }, {
                Log.d("lo", "getAllNameOfGroups: ${it.message}")
            })
    }
}