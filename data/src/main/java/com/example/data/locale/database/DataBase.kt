package com.example.data.locale.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.locale.convertors.DataConverter
import com.example.data.locale.dao.NoteDao
import com.example.domainn.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class DataBase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        const val DATA_BASE_NAME = "Notes"
    }
}