package com.example.notes.features.notedetails_feature

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domainn.entity.NoteEntity
import com.example.domainn.interactor.NotesInteractor
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class NoteDetailsViewModel @Inject constructor(
    private val interactor: NotesInteractor
) : ViewModel() {

    private var _noteEntity: MutableLiveData<NoteEntity> = MutableLiveData()
    val noteEntity: LiveData<NoteEntity>
        get() = _noteEntity

    private var _allNameOfGroups: MutableLiveData<List<String>> = MutableLiveData()
    val allNameOfGroups: LiveData<List<String>>
        get() = _allNameOfGroups

    fun getNoteById(id: Int) {
        interactor.getNoteById(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _noteEntity.value = it },
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