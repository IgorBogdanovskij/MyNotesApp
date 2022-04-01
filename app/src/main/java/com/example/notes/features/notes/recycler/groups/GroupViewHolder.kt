package com.example.notes.features.notes.recycler.groups

import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.GroupItemBinding

class GroupViewHolder(
    private val onGroupClickListener: OnGroupClickListener,
    private val groupItemViewBinding: GroupItemBinding,
    private val itemByPositionCallback: (Int) -> String
) : RecyclerView.ViewHolder(groupItemViewBinding.root) {

    init {
        groupItemViewBinding.root.setOnClickListener {
            onGroupClickListener.onGroupClick(itemByPositionCallback(adapterPosition), groupItemViewBinding)
        }
    }

    fun bind(groupName: String) {
        groupItemViewBinding.drawerText.text = groupName
    }
}
