package com.example.notes.data.locale.convertors

import androidx.room.TypeConverter
import java.util.*

class DataConverter {

    @TypeConverter
    fun toDate(timestamp:Long):Date {
        return (Date(timestamp))
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
}