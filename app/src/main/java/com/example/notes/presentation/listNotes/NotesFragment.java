//package com.example.notes.presentation.listNotes;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatDelegate;
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.Observer;
//import androidx.lifecycle.ViewModelProviders;
//import androidx.navigation.Navigation;
//
//import com.example.notes.App;
//import com.example.notes.R;
//import com.example.notes.SettingActivity;
//import com.example.notes.data.locale.entity.Note;
//import com.example.notes.presentation.edit.EditView;
//import com.example.notes.presentation.writeNote.NotesFragmentWrite;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.inject.Inject;
//
//public class NotesFragment extends Fragment {
//
//
//    private NotesViewModel mNotesViewModel;
//    private NotesView mNotesView;
//    private String mNameGroupDrawer;
//
//    @Inject
//    NotesViewModelKotlin mNotesViewModelKotlin;
//
//    public NotesFragment() {
//        super(R.layout.fragment_notes);
//
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        initDagger();
//    }
//
//    public void initDagger() {
//        ((App) requireActivity().getApplication()).appComponent.noteComponent().create().inject(this);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        mNotesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);
//
//        mNotesView = new NotesView(this, getActivity(), view, new NotesView.Callback() {
//            @Override
//            public void onFloatingButton() {
//                Navigation.findNavController(view).navigate(R.id.notesFragmentWrite);
//            }
//
//            @Override
//            public void onSwipeDeleteNote(String nameGroup, Note note) {
//                mNotesViewModel.deleteNote(note);
//            }
//
//            @Override
//            public void onClickNote(Note note) {
//
//                Navigation.findNavController(view).navigate(R.id.notesFragmentWrite,
//                        NotesFragmentWrite.argsOfNotes(note.getId()));
//            }
//
//            @Override
//            public void onLongClickNote(Note note) {
//                Navigation.findNavController(view).navigate(R.id.editFragment, EditView.argsOfNotes(note.getId()));
//            }
//
//            @Override
//            public void onClickGroupDrawer(String nameGroup) {
//
//                mNameGroupDrawer = nameGroup;
//                mNotesViewModel.setGroup(nameGroup);
//                mNotesViewModel.triggerLiveData();
//
//            }
//
//
//            @Override
//            public void restoreNote(Note note) {
//                mNotesViewModel.addNote(note);
//            }
//
//            @Override
//            public void textFromEditGroup(String text) {
//            }
//
//            @Override
//            public void onLongClickDrawerGroup() {
//                // TODO: 24/05/2021
//            }
//
//            @Override
//            public void onClickAllNotes(View view) {
//                mNotesViewModel.setGroup(null);
//                mNameGroupDrawer = null;
//                mNotesViewModel.triggerLiveData();
//            }
//
//            @Override
//            public void moveNotes(Note from, Note to) {
//                mNotesViewModel.moveNotes(from, to);
//
//            }
//
//            @Override
//            public void updateNote(Note note) {
//                mNotesViewModel.updateNote(note);
//            }
//
//            @Override
//            public void dayNight() {
//                if (NotesView.mDayNightMode) {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                    NotesView.mDayNightMode = false;
//
//                } else {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                    NotesView.mDayNightMode = true;
//                }
//
//
//            }
//
//            @Override
//            public void onSetting() {
//                Intent intent = new Intent(getActivity(), SettingActivity.class);
//                startActivity(intent);
//            }
//
//            @Override
//            public void deleteGroup(String nameGroup) {
//                List<Note> noteList = mNotesViewModel.getNotesWithoutLiveData();
//                List<Note> forDelete = new ArrayList<>();
//                for (Note note :
//                        noteList) {
//                    if (note.getNameGroup().equals(nameGroup)) {
//                        forDelete.add(note);
//                    }
//                }
//                mNotesViewModel.deleteNotes(forDelete);
//            }
//
//            @Override
//            public void onQueryTextChangeMy(String searchNotes) {
//                if (searchNotes.equals("")) {
//                    mNotesView.setNotes(mNotesViewModel.getNotesWithoutLiveData());
//
//                } else {
//                    mNotesView.getFilter().filter(searchNotes);
//                }
//            }
//        });
//
////        mNotesViewModel.getNotes().observe(getViewLifecycleOwner(), new Observer<List<Note>>() {
////            @Override
////            public void onChanged(List<Note> notes) {
////                List<Note> noteList = new ArrayList<>();
////                if (mNameGroupDrawer != null) {
////                    for (Note note :
////                            notes) {
////                        if (note.getNameGroup().equals(mNameGroupDrawer)) {
////                            noteList.add(note);
////                        }
////                    }
////                    mNotesView.setNotes(noteList);
////
////                } else {
////                    Log.d("ooo", "onChanged: ");
////
////                    mNotesView.setNotes(notes);
////                }
////            }
////        });
//
//
////        mNotesViewModel.getGroupNameFromNote().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
////            @Override
////            public void onChanged(List<String> groups) {
////                mNotesView.setGroupsDrawer(groups);
////            }
////        });
//
//        mNotesViewModelKotlin.getAllNews().observe(getViewLifecycleOwner(), new Observer<List<Note>>() {
//            @Override
//            public void onChanged(List<Note> notes) {
//                mNotesView.setNotes(notes);
//            }
//        });
//    }
//
//
//}
