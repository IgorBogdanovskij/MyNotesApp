//package com.example.notes.presentation.listGroup.touchHelper;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.ItemTouchHelper;
//import androidx.recyclerview.widget.RecyclerView;
//
//import static androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_DRAG;
//import static androidx.recyclerview.widget.ItemTouchHelper.DOWN;
//import static androidx.recyclerview.widget.ItemTouchHelper.UP;
//
//public class GroupItemTouchHelper extends ItemTouchHelper.Callback {
//
//    public interface Callback {
//        void move(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target);
//    }
//
//    public Callback mCallback;
//
//    public GroupItemTouchHelper(Callback callback) {
//        mCallback = callback;
//    }
//
//    @Override
//    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
//        return makeFlag(ACTION_STATE_DRAG, UP | DOWN);
//    }
//
//    @Override
//    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//        mCallback.move(recyclerView, viewHolder, target);
//        return true;
//    }
//
//    @Override
//    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//
//    }
//}
