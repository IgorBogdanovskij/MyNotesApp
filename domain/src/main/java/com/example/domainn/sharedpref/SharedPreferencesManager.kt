package com.example.domainn.sharedpref

interface SharedPreferencesManager {
    suspend fun putBoolean(value: Boolean)
    suspend fun checkLightTheme(): Boolean
}
