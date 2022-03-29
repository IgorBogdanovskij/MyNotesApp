//package com.example.notes.presentation.setting;
//
//import android.app.Application;
//
//import androidx.annotation.NonNull;
//import androidx.lifecycle.AndroidViewModel;
//
//import com.example.data.Repo;
//import com.example.data.RepoImp;
//
//public class PreferenceFragmentSettingsViewModel extends AndroidViewModel {
//    private Repo mRepo;
//    public PreferenceFragmentSettingsViewModel(@NonNull Application application) {
//        super(application);
//
//        mRepo = new RepoImp(application.getBaseContext());
//    }
//
//    public void putPrefString(String s){
//        mRepo.putPrefString(s);
//    }
//
//    public void putPrefInt(int color) {
//        mRepo.putPrefInt(color);
//    }
//}
