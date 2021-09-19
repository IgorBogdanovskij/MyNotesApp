//package com.example.notes.presentation.listNotes;
//
//import android.app.Application;
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//import androidx.lifecycle.AndroidViewModel;
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//
//import com.example.notes.data.locale.entity.Note;
//import com.example.notes.data.Repo;
//import com.example.notes.data.RepoImp;
//
//import java.util.Date;
//import java.util.List;
//
//public class NotesViewModel extends AndroidViewModel {
//
//    private final Repo mRepo;
//    private List<Note> group;
//    private MutableLiveData<String> mGroup;
//
//    public NotesViewModel(@NonNull Application application) {
//        super(application);
//        mRepo = new RepoImp(application.getApplicationContext());
//    }
//
//    public LiveData<List<Note>> getNotes() {
//        return mRepo.getNotes();
//    }
//
//    public List<Note> getNotesWithoutLiveData() {
//        return mRepo.getNotesWithoutLiveData();
//    }
//
//    public void deleteNote(Note note) {
//        mRepo.deleteNote(note);
//    }
//
//
//    public void deleteNotes(List<Note> note) {
//        mRepo.deleteNotes(note);
//    }
//
//    public void addNote(Note note) {
//        mRepo.addNote(note);
//    }
//
//
//    public void setGroup(String nameGroup) {
//        group = mRepo.getGroupByName(nameGroup);
//    }
//
//    public void triggerLiveData() {
//        Note note = new Note(100000, "test", "test", 0, 0, null);
//        mRepo.addNote(note);
//        mRepo.deleteNote(note);
//    }
//
//    public LiveData<List<String>> getGroupNameFromNote() {
//
//        return mRepo.getGroupsNameLiveData();
//    }
//
//    public void updateNote(Note note) {
//        mRepo.updateNote(note);
//    }
//
//    public void moveNotes(Note from, Note to) {
//        Date dataFrom;
//        Date dataTo;
//
//        dataFrom = from.getData();
//        dataTo = to.getData();
//
//        if (!dataFrom.toString().equals(dataTo.toString())) {
//
//            from.setData(dataTo);
//            Log.d("MyLog", "after_data_from = " + from.getData());
//
//            updateNote(from);
//
//            to.setData(dataFrom);
//            Log.d("MyLog", "after_data_from = " + to.getData());
//
//            updateNote(to);
//        }
//    }
//
//
//}
