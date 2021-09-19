package com.example.notes.data.locale.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.notes.data.locale.convertors.DataConverter
import com.example.notes.data.locale.dao.NoteDaoKotlin
import com.example.notes.data.locale.entity.NoteEntityKotlin

@Database(entities = [NoteEntityKotlin::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class DataBaseKotlin : RoomDatabase() {

    abstract fun noteDaoKotlin(): NoteDaoKotlin

    companion object {
        const val DATA_BASE_NAME = "Notes"
    }
}