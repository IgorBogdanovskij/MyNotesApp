package com.example.notes.presentation.writeNote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.data.locale.entity.NoteEntityKotlin
import com.example.notes.domain.interactor.INotesInteractor
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class NotesWriteViewModelKotlin @Inject constructor(
    private val interactor: INotesInteractor
) : ViewModel() {

    private var _noteEntityKotlin: MutableLiveData<NoteEntityKotlin> = MutableLiveData()
    val noteEntityKotlin: LiveData<NoteEntityKotlin>
        get() = _noteEntityKotlin

    private var _allNameOfGroups: MutableLiveData<List<String>> = MutableLiveData()
    val allNameOfGroups: LiveData<List<String>>
        get() = _allNameOfGroups

    fun getNoteById(id: Int) {
        interactor.getNoteById(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _noteEntityKotlin.value = it },
                { Log.d("lo", "getNoteById: ${it.message}") })
    }

    fun addNote(noteEntityKotlin: NoteEntityKotlin) {
        interactor
            .addNote(noteEntityKotlin).subscribeOn(Schedulers.io())
            .subscribe()
    }

    fun updateNote(noteEntityKotlin: NoteEntityKotlin) {
        interactor
            .updateNote(noteEntityKotlin)
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