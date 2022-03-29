package com.example.notes.features.notes_feature.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.example.notes.models.NoteUI

class DiffUtilCallback(private val oldList: List<NoteUI>, private val newList: List<NoteUI>) :
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