package com.example.domainn.sharedpref

interface SharedPreferencesManager {
    suspend fun putBoolean(value: Boolean)
    suspend fun putString(value: String)
    suspend fun checkLightTheme(): Boolean
    suspend fun checkLayoutManager(): String
}
