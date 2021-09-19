//package com.example.notes.presentation.listNotes;
//
//import android.app.Activity;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Filter;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.PopupMenu;
//import android.widget.SearchView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.ActionBarDrawerToggle;
//import androidx.constraintlayout.widget.ConstraintLayout;
//import androidx.drawerlayout.widget.DrawerLayout;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.ItemTouchHelper;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.notes.R;
//import com.example.notes.data.locale.entity.Note;
//import com.example.notes.presentation.listGroup.recycler.DrawerAdapter;
//import com.example.notes.presentation.listGroup.touchHelper.GroupItemTouchHelper;
//import com.example.notes.presentation.listNotes.recycler.NotesAdapter;
//import com.example.notes.presentation.listNotes.touchHelper.NotesItemTouchHelper;
//import com.google.android.material.appbar.MaterialToolbar;
//import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
//import com.google.android.material.snackbar.BaseTransientBottomBar;
//import com.google.android.material.snackbar.Snackbar;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class NotesView {
//
//    interface Callback {
//
//        void onFloatingButton();
//
//        void onSwipeDeleteNote(String nameGroup, Note note);
//
//        void onClickNote(Note note);
//
//        void onLongClickNote(Note note);
//
//        void onClickGroupDrawer(String s);
//
//        void restoreNote(Note note);
//
//        void textFromEditGroup(String text);
//
//        void onLongClickDrawerGroup();
//
//        void onClickAllNotes(View view);
//
//        void moveNotes(Note noteFrom, Note noteTarget);
//
//        void updateNote(Note note);
//
//        void dayNight();
//
//        void onSetting();
//
//        void deleteGroup(String nameGroup);
//
//        void onQueryTextChangeMy(String searchNotes);
//    }
//
//    private RecyclerView mRecyclerView;
//    private RecyclerView mRecyclerViewDrawer;
//    private NotesAdapter mNotesAdapter;
//    private DrawerAdapter mDrawerAdapter;
//    private ExtendedFloatingActionButton mFloatingActionButton;
//    private NotesItemTouchHelper mNotesItemTouchHelper;
//    private GroupItemTouchHelper mGroupItemTouchHelper;
//    private DrawerLayout mDrawerLayout;
//    private MaterialToolbar mToolbar;
//    private ImageButton mImageButton;
//    private PopupMenu mPopupMenuDrawer;
//    private PopupMenu mPopupMenuNotesAdapter;
//    private LinearLayout mLinearLayout;
//    private LinearLayout mLinearLayoutSetting;
//    private ArrayList<View> mListViewDrawer;
//    private int mFromPosition;
//    private int mToPosition;
//    private ImageView mImageView;
//    public static boolean mDayNightMode;
//    ItemTouchHelper itemTouchHelper;
//    private TextView mTextView;
//    private ConstraintLayout mConstraintLayout;
//    private ConstraintLayout mConstraintLayoutDrawer;
//
//    public NotesView(Fragment fragment, Activity activity, View view, Callback callback) {
//        mFloatingActionButton = view.findViewById(R.id.floatingButtonNotes);
//        mRecyclerView = view.findViewById(R.id.myRecyclerViewForNotes);
//        mLinearLayout = view.findViewById(R.id.linearNote);
//        mRecyclerViewDrawer = view.findViewById(R.id.DrawerRecyclerView);
//        mDrawerLayout = activity.findViewById(R.id.myDrawerLayout);
//        mToolbar = view.findViewById(R.id.toolbarNotes);
//        mImageView = view.findViewById(R.id.day_night_mode);
//        mLinearLayoutSetting = view.findViewById(R.id.setting);
//        mConstraintLayout = view.findViewById(R.id.notes_is_empty);
//        mConstraintLayoutDrawer = view.findViewById(R.id.groups_is_empty);
//        mTextView = view.findViewById(R.id.groups);
//        mListViewDrawer = new ArrayList<>();
//
//
//        if (mDayNightMode) {
//            mImageView.setBackgroundResource(R.drawable.ic_light_mode_black_24dp);
//        } else {
//            mImageView.setBackgroundResource(R.drawable.ic_mode_night_black_24dp);
//        }
//
//        mDrawerAdapter = new DrawerAdapter(new DrawerAdapter.Callback() {
//            @Override
//            public void onClickDrawer(View view1, String s) {
//
//                mListViewDrawer.add(view1);
//                for (View view2 :
//                        mListViewDrawer) {
//                    if (view2 == view1) {
//                        view1.setBackgroundResource(R.drawable.select_item);
//                    } else {
//                        view2.setBackgroundResource(0);
//                    }
//                }
//                callback.onClickGroupDrawer(s);
//            }
//
//
//            @Override
//            public void onLongClick(View v, String nameGroup) {
//
//                mPopupMenuDrawer = new PopupMenu(v.getContext(), v);
//                mPopupMenuDrawer.getMenuInflater().inflate(R.menu.popup_menu_drawer, mPopupMenuDrawer.getMenu());
//                mPopupMenuDrawer.setOnMenuItemClickListener(item -> {
//                    if (item.getItemId() == R.id.editPopupDrawer) {
//
//                        callback.onLongClickDrawerGroup();
//                        return true;
//                    } else if (item.getItemId() == R.id.deletePopupDrawer) {
//                        callback.deleteGroup(nameGroup);
//                        return true;
//                    }
//                    return false;
//                });
//                mPopupMenuDrawer.show();
//            }
//        });
//        mRecyclerViewDrawer.setLayoutManager(new LinearLayoutManager(view.getContext()));
//        mRecyclerViewDrawer.setAdapter(mDrawerAdapter);
//
//        mNotesAdapter = new NotesAdapter(new NotesAdapter.Callback() {
//            @Override
//            public void onClick(Note note) {
//                callback.onClickNote(note);
//            }
//
//            @Override
//            public void onLongClick(View view1, Note note, RecyclerView.ViewHolder vh) {
//                itemTouchHelper.startDrag(vh);
//
//                mPopupMenuNotesAdapter = new PopupMenu(view.getContext(), view1);
//                mPopupMenuNotesAdapter.getMenuInflater().inflate(R.menu.popup_menu, mPopupMenuNotesAdapter.getMenu());
//                mPopupMenuNotesAdapter.setOnMenuItemClickListener(item -> {
//                    if (item.getItemId() == R.id.editPopup) {
//                        callback.onLongClickNote(note);
//                        return true;
//                    } else if (item.getItemId() == R.id.deletePopup) {
//
//                        callback.onSwipeDeleteNote(note.getNameGroup(), note);
//                    }
//                    return false;
//                });
//                mPopupMenuNotesAdapter.show();
//            }
//        });
//
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
//        mRecyclerView.setAdapter(mNotesAdapter);
//
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(activity, mDrawerLayout, mToolbar,
//                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        mDrawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
//        mGroupItemTouchHelper = new GroupItemTouchHelper(new GroupItemTouchHelper.Callback() {
//            @Override
//            public void move(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                mPopupMenuDrawer.dismiss();
//                int fromPosition = viewHolder.getAdapterPosition();
//                int toPosition = target.getAdapterPosition();
//                Collections.swap(mDrawerAdapter.getListNotes(), fromPosition, toPosition);
//                mDrawerAdapter.notifyItemMoved(fromPosition, toPosition);
//            }
//        });
//
//        ItemTouchHelper itemTouchHelperDrawer = new ItemTouchHelper(mGroupItemTouchHelper);
//        itemTouchHelperDrawer.attachToRecyclerView(mRecyclerViewDrawer);
//
//        mNotesItemTouchHelper = new NotesItemTouchHelper(new NotesItemTouchHelper.Callback() {
//            @Override
//            public void onSwipe(int position) {
//                Note note = mNotesAdapter.getNoteFromAdapter(position);
//                Snackbar snackbar = Snackbar.make(view, "Are you sure", BaseTransientBottomBar.LENGTH_LONG);
//                snackbar.setAction("Undo", v -> callback.restoreNote(note));
//                snackbar.show();
//                if (note.getNameGroup() != null) {
//                    callback.onSwipeDeleteNote(note.getNameGroup(), note);
//                } else {
//                    callback.onSwipeDeleteNote("", note);
//                }
//            }
//
//
//            @Override
//            public void move(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                mPopupMenuNotesAdapter.dismiss();
//                mFromPosition = viewHolder.getAdapterPosition();
//                mToPosition = target.getAdapterPosition();
//                Note from = mNotesAdapter.getListNotes().remove(mFromPosition);
//                mNotesAdapter.getListNotes().add(mToPosition, from);
//                callback.moveNotes(mNotesAdapter.getListNotes().get(mFromPosition), mNotesAdapter.getListNotes().get(mToPosition));
//                mNotesAdapter.notifyItemMoved(mFromPosition, mToPosition);
//            }
//
//            @Override
//            public void clearView() {
//            }
//
//        });
//        itemTouchHelper = new ItemTouchHelper(mNotesItemTouchHelper);
//        itemTouchHelper.attachToRecyclerView(mRecyclerView);
//        mLinearLayout.setOnClickListener(v -> {
//            mListViewDrawer.add(v);
//            for (View view1 :
//                    mListViewDrawer) {
//                if (view1 == v) {
//                    view1.setBackgroundResource(R.drawable.select_item);
//                } else {
//                    view1.setBackgroundResource(0);
//                }
//            }
//
//            callback.onClickAllNotes(v);
//        });
//
//        MenuItem searchItem = mToolbar.getMenu().findItem(R.id.action_search);
//        android.widget.SearchView searchView = (android.widget.SearchView) searchItem.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                callback.onQueryTextChangeMy(newText);
//                return true;
//            }
//        });
//        mLinearLayoutSetting.setOnClickListener(v -> callback.onSetting());
//        mFloatingActionButton.setOnClickListener(v -> callback.onFloatingButton());
//        mImageView.setOnClickListener(v -> callback.dayNight());
////        mImageButton.setOnClickListener(v -> {
////            AlertDialogNewGroup alertDialogNewGroup = new AlertDialogNewGroup(editText -> callback.textFromEditGroup(editText));
////            alertDialogNewGroup.show(fragment.getParentFragmentManager(), "");
////        });
//    }
//
//
//    public Filter getFilter() {
//        return mNotesAdapter.getFilter();
//    }
//
//    public void setNotes(List<Note> notes) {
//        if (notes.isEmpty()) {
//            mConstraintLayout.setVisibility(View.VISIBLE);
//        } else {
//            mConstraintLayout.setVisibility(View.GONE);
//        }
//        mNotesAdapter.setNotes(notes);
//    }
//
//
//    public void setGroupsDrawer(List<String> nameGroup) {
//        if (nameGroup.isEmpty()) {
//            mConstraintLayoutDrawer.setVisibility(View.VISIBLE);
//        } else {
//            mConstraintLayoutDrawer.setVisibility(View.GONE);
//        }
//        mDrawerAdapter.setGroupsDrawer(nameGroup);
//    }
//}
