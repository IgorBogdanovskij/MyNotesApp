package com.example.notes.presentation.listGroup.recycler

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.ItemDrawerAdapterBinding

class NavigationViewDrawerKotlin(
    private val onClickListener: OnClickListenerDrawer
) : RecyclerView.Adapter<NavigationViewDrawerKotlin.ViewHolder>() {

    private var listNotes = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDrawerAdapterBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listNotes[position])
    }

    override fun getItemCount() = listNotes.size

    fun setData(list: List<String>) {
        listNotes = list
        notifyDataSetChanged()
    }

    interface OnClickListenerDrawer {
        fun onClick(note: String, linearLayout: LinearLayout)
    }

    inner class ViewHolder(private val viewBinding: ItemDrawerAdapterBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        init {
            viewBinding.root.setOnClickListener {
                onClickListener.onClick(listNotes[adapterPosition], viewBinding.root)
            }
        }

        fun bind(groupName: String) {
            viewBinding.drawerText.text = groupName
        }
    }
}