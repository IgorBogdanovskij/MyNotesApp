//package com.example.notes.data;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//
//import androidx.lifecycle.LiveData;
//
//import com.example.notes.data.locale.database.DataBase;
//import com.example.notes.data.locale.entity.Note;
//
//import java.util.List;
//
//public class RepoImp implements Repo {
//
//
//    private DataBase mDataBase;
//    private  SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;
//
//
//    public RepoImp(Context context) {
//        mDataBase = DataBase.getInstance(context);
//
//        if (mSharedPreferences == null){
//            mSharedPreferences = context.getSharedPreferences("setting", Context.MODE_PRIVATE);
//        }
//    }
//
//
//
//
//    @Override
//    public LiveData<List<Note>> getNotes() {
//        return mDataBase.getNoteDao().listOfAllNotesLiveData();
//    }
//
//    @Override
//    public List<Note> getNotesWithoutLiveData() {
//        return mDataBase.getNoteDao().listOfAllNoteWithoutLiveData();
//    }
//
//    @Override
//    public Note getNote(int id) {
//        return mDataBase.getNoteDao().noteById(id);
//    }
//
//
//    @Override
//    public void deleteNote(Note noteEntityKotlin)
//    {
//        mDataBase.getNoteDao().deleteNote(noteEntityKotlin);
//    }
//
//
//
//    @Override
//    public void deleteNotes(List<Note> noteEntityKotlin) {
//        mDataBase.getNoteDao().deleteNotes(noteEntityKotlin);
//    }
//
//
//
//    @Override
//    public void addNote(Note noteEntityKotlin) {
//        mDataBase.getNoteDao().insertNote(noteEntityKotlin);
//    }
//
//    @Override
//    public void updateNote(Note noteEntityKotlin) {
//        mDataBase.getNoteDao().updateNote(noteEntityKotlin);
//    }
//
//    @Override
//    public void updateNoteList(List<Note> noteEntityKotlin) {
//        mDataBase.getNoteDao().updateNoteList(noteEntityKotlin);
//    }
//
//
//
//
//    @Override
//    public List<Note> getGroupByName(String nameGroup) {
//
//        return mDataBase.getNoteDao().listOfAllNoteByNameGroup(nameGroup);
//    }
//
//
//
//
//
//    @Override
//    public void putPrefString(String s) {
//        mEditor = mSharedPreferences.edit();
//        mEditor.putString("test", s);
//        mEditor.apply();
//    }
//
//    @Override
//    public String getPrefString() {
//        return mSharedPreferences.getString("test", "");
//    }
//
//    @Override
//    public void putPrefInt(int color) {
//        mEditor = mSharedPreferences.edit();
//        mEditor.putInt("test1", color);
//        mEditor.apply();
//    }
//
//    @Override
//    public int getPrefInt(int color) {
//        return mSharedPreferences.getInt("test1", 0);
//    }
//
//
//
//    @Override
//    public LiveData<List<String>> getGroupsNameLiveData() {
//        return mDataBase.getNoteDao().listOfAllGroupsName();
//    }
//
//
//}
