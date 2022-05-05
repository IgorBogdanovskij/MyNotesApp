package com.example.notes.features.notes.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domainn.entity.NoteEntity
import com.example.domainn.interactor.NotesInteractor
import com.example.domainn.interactor.SharedPreferencesInteractor
import com.example.notes.mappers.mapItemToNoteUI
import com.example.notes.mappers.mapListToNoteUI
import com.example.notes.models.NoteUi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val interactor: NotesInteractor,
    private val sharedPreferencesInteractor: SharedPreferencesInteractor
) : ViewModel() {

    private var _allNotes: MutableLiveData<State> = MutableLiveData()
    val allNotes: LiveData<State>
        get() = _allNotes

    private var _allNotesByGroup: MutableLiveData<List<NoteUi>> = MutableLiveData()
    val allNotesByGroup: LiveData<List<NoteUi>>
        get() = _allNotesByGroup

    private var _allNameOfGroups: MutableLiveData<List<String>> = MutableLiveData()
    val allNameOfGroups: LiveData<List<String>>
        get() = _allNameOfGroups

    private var _noteEntity: MutableLiveData<NoteUi> = MutableLiveData()
    val noteUi: LiveData<NoteUi>
        get() = _noteEntity

    fun getAllNotes() {
        interactor.getAllNotes()
            .observeOn(AndroidSchedulers.mainThread())
            .map(::mapListToNoteUI)
            .subscribe({
                if (it.isNullOrEmpty()) {
                    _allNotes.value = State.Error("There are not any notes")
                } else {
                    _allNotes.value = State.Success(it)
                }
            }, {
                _allNotes.value = State.Error("${it.message}")
            })
    }

    fun deleteNote(id: Int) {
        interactor
            .deleteNote(id)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                getAllNotes()
                getAllNameOfGroups()
            }
            .subscribe()
    }

    fun addNote(noteEntity: NoteEntity) {
        interactor
            .addNote(noteEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                getAllNotes()
                getAllNameOfGroups()
            }
            .subscribe()
    }

    private fun updateNote(noteEntity: NoteEntity) {
        interactor
            .updateNote(noteEntity)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                getAllNameOfGroups()
            }
            .subscribe()
    }

    fun moveNotes(noteEntityFrom: NoteEntity, noteEntityTo: NoteEntity) {

        val dataFrom: Date = noteEntityFrom.data;
        val dataTo: Date = noteEntityTo.data;

        if (dataFrom.toString() != dataTo.toString()) {
            noteEntityFrom.data = dataTo;
            updateNote(noteEntityFrom);
            noteEntityTo.data = dataFrom;
            updateNote(noteEntityTo);
        }
    }

    fun getAllNewsByGroup(nameGroup: String) {
        interactor.getAllNotesByGroup(nameGroup = nameGroup)
            .observeOn(AndroidSchedulers.mainThread())
            .map(::mapListToNoteUI)
            .subscribe({
                _allNotesByGroup.value = it
            }, {
                Log.d("lo", "getAllNewsByGroup: ${it.message}")
            })
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

    fun getNoteById(id: Int) {
        interactor.getNoteById(id)
            .observeOn(AndroidSchedulers.mainThread())
            .map(::mapItemToNoteUI)
            .subscribe({
                _noteEntity.value = it
            }, {
                Log.d("lo", "getNoteById: ${it.message}")
            })
    }

    fun checkLightTheme(): Boolean {
        return runBlocking { sharedPreferencesInteractor.checkLightTheme() }
    }

    fun putBoolean(lightTheme: Boolean) {
        viewModelScope.launch {
            sharedPreferencesInteractor.putBoolean(lightTheme)
        }
    }
}
