package com.example.notes.presentation.listNotes.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.data.locale.entity.NoteEntityKotlin
import com.example.notes.databinding.ItemNotesBinding
import com.example.notes.presentation.listNotes.diffutils.DiffUtilCallback

class NotesAdapterKotlin(private val callback: Callback) :
    RecyclerView.Adapter<NotesAdapterKotlin.ViewHolder>() {


    private var listNotesOld = mutableListOf<NoteEntityKotlin>()

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int) = listNotesOld[position].id.toLong()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNotesBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listNotesOld[position])
    }

    override fun getItemCount() = listNotesOld.size

    fun setData(list: List<NoteEntityKotlin>) {
        val diff = DiffUtilCallback(listNotesOld, list)
        val diffResult = DiffUtil.calculateDiff(diff)
        listNotesOld = list.toMutableList()
        diffResult.dispatchUpdatesTo(this)
    }

    fun getNoteByPosition(position: Int) = listNotesOld[position]

    fun getListNotes() = listNotesOld

    interface Callback {
        fun onClick(noteEntityKotlin: NoteEntityKotlin)
        fun onLongClick(noteEntityKotlin: NoteEntityKotlin, view: View)
    }

    inner class ViewHolder(private val view: ItemNotesBinding) :
        RecyclerView.ViewHolder(view.root) {

        init {
            view.root.setOnClickListener {
                callback.onClick(listNotesOld[adapterPosition])
            }

            view.root.setOnLongClickListener {
                callback.onLongClick(listNotesOld[adapterPosition], view.root)

                return@setOnLongClickListener true
            }
        }

        fun bind(noteEntityKotlin: NoteEntityKotlin) {
            view.textForAdapterItemTitle.text = noteEntityKotlin.title
            view.textForAdapterItemDescription.text = noteEntityKotlin.description
            if (noteEntityKotlin.nameGroup.isEmpty()) {
                view.groupName.visibility = View.GONE
            } else {
                view.groupName.visibility = View.VISIBLE
                view.groupName.text = noteEntityKotlin.nameGroup
            }
        }
    }
}