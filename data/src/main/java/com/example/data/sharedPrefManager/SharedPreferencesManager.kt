package com.example.data.sharedPrefManager

interface SharedPreferencesManager {
    suspend fun putBoolean(value: Boolean)
    suspend fun putString(value: String)
    suspend fun getBoolean(): Boolean
    suspend fun getString(): String
}
