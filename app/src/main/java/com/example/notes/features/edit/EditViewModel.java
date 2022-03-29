//package com.example.notes.presentation.edit;
//
//import android.app.Application;
//
//import androidx.annotation.NonNull;
//import androidx.lifecycle.AndroidViewModel;
//import androidx.lifecycle.LiveData;
//
//import com.example.data.locale.entity.Note;
//import com.example.data.Repo;
//import com.example.data.RepoImp;
//
//import java.util.List;
//
//public class EditViewModel extends AndroidViewModel {
//    private Repo mRepo;
//
//    public EditViewModel(@NonNull Application application) {
//        super(application);
//        mRepo = new RepoImp(application.getApplicationContext());
//    }
//
//
//
//
//    public LiveData<List<String>> getGroupNameFromNote(){
//
//        return mRepo.getGroupsNameLiveData();
//    }
//
//
//
//
//    public Note getNote(int id) {
//        return mRepo.getNote(id);
//    }
//
//    public void updateNote(Note noteEntityKotlin)
//    {
//        mRepo.updateNote(noteEntityKotlin);
//    }
//
//}
