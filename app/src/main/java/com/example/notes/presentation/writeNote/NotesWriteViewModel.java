//package com.example.notes.presentation.writeNote;
//
//import android.app.Application;
//
//import androidx.annotation.NonNull;
//import androidx.lifecycle.AndroidViewModel;
//import androidx.lifecycle.LiveData;
//
//import com.example.notes.data.locale.entity.Note;
//import com.example.notes.data.Repo;
//import com.example.notes.data.RepoImp;
//
//import java.util.List;
//
//public class NotesWriteViewModel extends AndroidViewModel {
//    private Repo repo;
//
//    public NotesWriteViewModel(@NonNull Application application) {
//        super(application);
//
//        repo = new RepoImp(application.getApplicationContext());
//    }
//
//
//    public Note getNote(int id) {
//        return repo.getNote(id);
//    }
//
//    public void addNote(Note note) {
//        repo.addNote(note);
//    }
//
//    public void update(Note note) {
//        repo.updateNote(note);
//    }public LiveData<List<String>> getGroupNameFromNote(){
//
//        return repo.getGroupsNameLiveData();
//    }
//
//}
