package com.example.notes.features.notes.recycler.groups

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.notes.databinding.GroupItemBinding

class GroupsAdapter(
    private val onGroupClickListener: OnGroupClickListener
) : ListAdapter<String, GroupViewHolder>(object : DiffUtil.ItemCallback<String>(){

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val binding = GroupItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return GroupViewHolder(onGroupClickListener, binding, ::getItem)
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
