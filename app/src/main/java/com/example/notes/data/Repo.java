//package com.example.notes.data;
//
//import androidx.lifecycle.LiveData;
//
//import com.example.notes.data.locale.entity.Note;
//
//import java.util.List;
//
//public interface Repo {
//    LiveData<List<Note>> getNotes();
//
//    List<Note> getNotesWithoutLiveData();
//
//    LiveData<List<String>> getGroupsNameLiveData();
//
//    Note getNote(int id);
//
//    void deleteNote(Note noteEntityKotlin);
//
//    void deleteNotes(List<Note> noteEntityKotlin);
//
//    void addNote(Note noteEntityKotlin);
//
//    void updateNote(Note noteEntityKotlin);
//
//    void updateNoteList(List<Note> noteEntityKotlin);
//
//    List<Note> getGroupByName(String nameGroup);
//
//    void putPrefString(String s);
//
//    String getPrefString();
//
//    void putPrefInt(int color);
//
//    int getPrefInt(int color);
//}
