//package com.example.notes.data.locale.database;
//
//import android.content.Context;
//
//import androidx.room.Database;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//import androidx.room.TypeConverters;
//
//import com.example.notes.data.DataConverter;
//import com.example.notes.data.locale.dao.NoteDAO;
//import com.example.notes.data.locale.entity.Note;
//
//@Database(entities = {Note.class}, version = 1, exportSchema = false)
//@TypeConverters({DataConverter.class, TypeConverterList.class })
//
//public abstract class DataBase extends RoomDatabase {
//
//    private static final Object LOCK = new Object();
//    private static final String DATABASE_NAME = "Notes";
//    private static DataBase sInstance;
//
//        public static DataBase getInstance(Context context){
//            if (sInstance == null){
//                synchronized (LOCK){
//                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
//                            DataBase.class, DataBase.DATABASE_NAME)
//                            .allowMainThreadQueries()
//                            .addMigrations()
//                            .build();
//                }
//            }
//            return sInstance;
//        }
//       // public abstract TaskDAO getTaskDao();
//        public abstract NoteDAO getNoteDao();
//}
