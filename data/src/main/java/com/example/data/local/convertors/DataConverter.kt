package com.example.data.local.convertors

import androidx.room.TypeConverter
import java.util.*

class DataConverter {

    @TypeConverter
    fun toDate(timestamp:Long):Date {
        return (Date(timestamp))
    }

    @TypeConverter
    fun toMilliseconds(date: Date?): Long? {
        return date?.time
    }
}