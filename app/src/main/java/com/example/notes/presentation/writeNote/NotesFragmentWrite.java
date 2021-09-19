//package com.example.notes.presentation.writeNote;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.Observer;
//import androidx.lifecycle.ViewModelProviders;
//import androidx.navigation.Navigation;
//
//import com.example.notes.R;
//
//import java.util.List;
//
//
//public class NotesFragmentWrite extends Fragment {
//
//
//    public static Bundle argsOfNotes(int id) {
//        Bundle bundle = new Bundle();
//        bundle.putInt("id", id);
//        return bundle;
//    }
//
//    private NotesWriteViewModel mNotesViewModel;
//    private NotesViewWrite mNotesViewWrite;
//
//    public NotesFragmentWrite() {
//        super(R.layout.fragment_notes_write);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        mNotesViewModel = ViewModelProviders.of(this).get(NotesWriteViewModel.class);
//        mNotesViewWrite = new NotesViewWrite(getActivity(), requireView(), (id, note) -> {
//            if (id == 0) {
//                if (!note.getTitle().isEmpty() && !note.getDescription().isEmpty()) {
//                    mNotesViewModel.addNote(note);
//                    Navigation.findNavController(view).popBackStack();
//
//                } else {
//                    Toast.makeText(view.getContext(), "Write something", Toast.LENGTH_SHORT).show();
//                }
//            } else {
//                if (!note.getTitle().isEmpty() && !note.getDescription().isEmpty()) {
//
//                    mNotesViewModel.update(note);
//                    Navigation.findNavController(view).popBackStack();
//                } else {
//                    Toast.makeText(view.getContext(), "Write something", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//        });
//        getBundle();
//
//
//        mNotesViewModel.getGroupNameFromNote().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
//            @Override
//            public void onChanged(List<String> list) {
//                mNotesViewWrite.setListAndCreateAdapter(list);
//            }
//        });
//    }
//
//
//    private void getBundle() {
//        if (getArguments() != null) {
//            int id = getArguments().getInt("id");
//            mNotesViewWrite.setNote(mNotesViewModel.getNote(id));
//        } else {
//            mNotesViewWrite.setNote(null);
//        }
//
//    }
//}
