package com.example.notes.presentation.listNotes.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.example.notes.data.locale.entity.NoteEntityKotlin

class DiffUtilCallback(private val oldList: List<NoteEntityKotlin>, private val newList: List<NoteEntityKotlin>) :
    DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> {
                return false
            }
            oldList[oldItemPosition].title != newList[newItemPosition].title -> {
                return false
            }
            oldList[oldItemPosition].description != newList[newItemPosition].description -> {
                return false
            }
            else -> true
        }
    }
}