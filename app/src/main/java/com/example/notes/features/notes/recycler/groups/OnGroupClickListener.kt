package com.example.notes.features.notes.recycler.groups

import com.example.notes.databinding.GroupItemBinding

interface OnGroupClickListener {
    fun onGroupClick(nameGroup: String, groupItemViewBinding: GroupItemBinding)
}
