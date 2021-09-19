package com.example.notes.presentation.listNotes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.data.locale.entity.NoteEntityKotlin
import com.example.notes.domain.interactor.INotesInteractor
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class NotesViewModelKotlin @Inject constructor(
    private val interactor: INotesInteractor
) : ViewModel() {

    private var _allNotes: MutableLiveData<List<NoteEntityKotlin>> = MutableLiveData()
    val allNotes: LiveData<List<NoteEntityKotlin>>
        get() = _allNotes

    private var _allNotesByGroup: MutableLiveData<List<NoteEntityKotlin>> = MutableLiveData()
    val allNotesByGroup: LiveData<List<NoteEntityKotlin>>
        get() = _allNotesByGroup

    private var _allNameOfGroups: MutableLiveData<List<String>> = MutableLiveData()
    val allNameOfGroups: LiveData<List<String>>
        get() = _allNameOfGroups

    private var _noteEntityKotlin: MutableLiveData<NoteEntityKotlin> = MutableLiveData()
    val noteEntityKotlin: LiveData<NoteEntityKotlin>
        get() = _noteEntityKotlin

    init {
        getAllNews()
        getAllNameOfGroups()
    }

    fun getAllNews() {
        interactor.getAllNotes()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _allNotes.value = it
            }, {
                Log.d("lol", "getAllNews: ${it.message}")
            })
    }

    fun deleteNote(noteEntityKotlin: NoteEntityKotlin) {
        interactor
            .deleteNote(noteEntityKotlin)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                getAllNews()
                getAllNameOfGroups()
            }
            .subscribe()
    }

    fun addNote(noteEntityKotlin: NoteEntityKotlin) {
        interactor
            .addNote(noteEntityKotlin).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                getAllNews()
                getAllNameOfGroups()
            }
            .subscribe()
    }

    fun updateNote(noteEntityKotlin: NoteEntityKotlin) {
        interactor
            .updateNote(noteEntityKotlin)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                getAllNameOfGroups()
            }
            .subscribe()
    }

    fun moveNotes(noteEntityKotlinFrom: NoteEntityKotlin, noteEntityKotlinTo: NoteEntityKotlin) {

        val dataFrom: Date = noteEntityKotlinFrom.data;
        val dataTo: Date = noteEntityKotlinTo.data;

        if (dataFrom.toString() != dataTo.toString()) {

            noteEntityKotlinFrom.data = dataTo;

            updateNote(noteEntityKotlinFrom);

            noteEntityKotlinTo.data = dataFrom;

            updateNote(noteEntityKotlinTo);
        }
    }

    fun getAllNewsByGroup(nameGroup: String) {
        interactor.getAllNotesByGroup(nameGroup = nameGroup)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _allNotesByGroup.value = it
            }, {
                Log.d("lo", "getAllNewsByGroup: ${it.message}")
            })
    }

    private fun getAllNameOfGroups() {
        interactor.getAllNotesWithNameGroup()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _allNameOfGroups.value = it
            }, {
                Log.d("lo", "getAllNameOfGroups: ${it.message}")
            })
    }

    fun getNoteById(id:Int){
        interactor.getNoteById(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _noteEntityKotlin.value = it
            },{
                Log.d("lo", "getNoteById: ${it.message}")
            })
    }
}