//package com.example.notes.data.locale.dao;
//
//import androidx.lifecycle.LiveData;
//import androidx.room.Dao;
//import androidx.room.Delete;
//import androidx.room.Insert;
//import androidx.room.OnConflictStrategy;
//import androidx.room.Query;
//import androidx.room.Update;
//
//import com.example.notes.data.locale.entity.Note;
//
//import java.util.List;
//
//@Dao
//public interface NoteDAO {
//    @Query("SELECT * FROM NoteEntityKotlin ORDER BY data")
//    LiveData<List<Note>> listOfAllNotesLiveData();
//
//    @Query("SELECT * FROM NoteEntityKotlin ORDER BY data")
//    List<Note> listOfAllNoteWithoutLiveData();
//
//    @Query("SELECT * FROM NoteEntityKotlin WHERE nameGroup == :nameGroup ORDER BY data")
//    List<Note> listOfAllNoteByNameGroup(String nameGroup);
//
//    @Query("SELECT DISTINCT nameGroup FROM NoteEntityKotlin WHERE nameGroup !=''  ORDER BY data")
//    LiveData<List<String>> listOfAllGroupsName();
//
//    @Query("SELECT * FROM NoteEntityKotlin WHERE id = :id")
//    Note noteById(int id);
//
//    @Insert
//    void insertNote(Note noteEntityKotlinEntry);
//
//    @Update
//    void updateNote(Note noteEntityKotlinEntry);
//
//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    void updateNoteList(List<Note> noteEntityKotlinEntry);
//
//    @Delete
//    void deleteNote(Note noteEntityKotlinEntry);
//
//    @Delete
//    void deleteNotes(List<Note> noteEntityKotlinEntry);
//
//}
//
//
