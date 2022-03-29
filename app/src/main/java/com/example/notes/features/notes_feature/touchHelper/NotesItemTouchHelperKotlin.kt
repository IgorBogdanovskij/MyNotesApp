package com.example.notes.features.notes_feature.touchHelper

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class NotesItemTouchHelperKotlin(private val callback: Callback) : ItemTouchHelper.Callback() {

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeFlag(
            ItemTouchHelper.ACTION_STATE_DRAG,
            ItemTouchHelper.UP or ItemTouchHelper.DOWN
        ) or makeFlag(
            ItemTouchHelper.ACTION_STATE_SWIPE,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        )
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return true
    }

    override fun onMoved(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        fromPos: Int,
        target: RecyclerView.ViewHolder,
        toPos: Int,
        x: Int,
        y: Int
    ) {
        super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y)

        callback.onMove(viewHolder, target)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) =
        callback.onSwipe(viewHolder.adapterPosition)

    interface Callback {
        fun onSwipe(position: Int)
        fun onMove(
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        )
    }
}