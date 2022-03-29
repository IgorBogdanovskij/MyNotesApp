package com.example.notes.features.notes_feature.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.ItemNotesBinding
import com.example.notes.models.NoteUI
import com.example.notes.features.notes_feature.diffutils.DiffUtilCallback

class NotesAdapter(
    private val callback: Callback
) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private var listNotesOld = mutableListOf<NoteUI>()

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

    fun setData(list: List<NoteUI>) {
        val diff = DiffUtilCallback(listNotesOld, list)
        val diffResult = DiffUtil.calculateDiff(diff)
        listNotesOld = list.toMutableList()
        diffResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }

    fun getNoteByPosition(position: Int) = listNotesOld[position]

    fun getListNotes() = listNotesOld

    interface Callback {
        fun onClick(noteEntity: NoteUI)
        fun onLongClick(noteEntity: NoteUI, view: View)
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

        fun bind(noteEntity: NoteUI) {
            view.textForAdapterItemTitle.text = noteEntity.title
            view.textForAdapterItemDescription.text = noteEntity.description
            if (noteEntity.nameGroup.isEmpty()) {
                view.groupName.visibility = View.GONE
            } else {
                view.groupName.visibility = View.VISIBLE
                view.groupName.text = noteEntity.nameGroup
            }
        }
    }
}