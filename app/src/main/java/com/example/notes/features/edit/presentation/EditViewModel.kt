package com.example.notes.features.edit.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domainn.entity.NoteEntity
import com.example.domainn.interactor.NotesInteractor
import com.example.notes.mappers.mapItemToNoteUI
import com.example.notes.models.NoteUi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditViewModel @Inject constructor(
    private val notesInteractor: NotesInteractor
) : ViewModel() {

    private var _allNameOfGroups: MutableLiveData<List<String>> = MutableLiveData()
    val allNameOfGroups: LiveData<List<String>> get() = _allNameOfGroups

    private var _noteUi: MutableLiveData<NoteUi> = MutableLiveData()
    val noteUi: LiveData<NoteUi> get() = _noteUi

    fun getNoteById(id: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            notesInteractor.getNoteByIdFlow(id = id)
                .map(::mapItemToNoteUI)
                .collect { note -> _noteUi.value = note }
        }
    }

    fun updateNote(noteEntity: NoteEntity) {
        notesInteractor.updateNote(noteEntity)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun getAllNameOfGroups() {
        notesInteractor.getAllNotesWithNameGroup()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _allNameOfGroups.value = it
            }, {
                Log.d("lo", "getAllNameOfGroups: ${it.message}")
            })
    }
}
