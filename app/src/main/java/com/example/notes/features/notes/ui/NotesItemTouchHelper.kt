package com.example.notes.features.notes.ui

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class NotesItemTouchHelper(
    private val notesItemTouchHelperCallback: NotesItemTouchHelperCallback
) : ItemTouchHelper.Callback() {

    private var fromAdapterPosition = -1
    private var toAdapterPosition = -1

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
    ): Boolean = true

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
        fromAdapterPosition = viewHolder.adapterPosition
        toAdapterPosition = target.adapterPosition
        notesItemTouchHelperCallback.onMoved(viewHolder, target)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        notesItemTouchHelperCallback.onSwipe(viewHolder.adapterPosition)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        notesItemTouchHelperCallback.onEndAction(fromAdapterPosition, toAdapterPosition)
        fromAdapterPosition = -1
        toAdapterPosition = -1
        super.clearView(recyclerView, viewHolder)
    }

    interface NotesItemTouchHelperCallback {
        fun onSwipe(position: Int)
        fun onMoved(viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder)
        fun onEndAction(fromAdapterPosition: Int, toAdapterPosition: Int)
    }
}
