package com.example.data.sharedPrefManager

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferencesManagerImpl @Inject constructor(
    applicationContext: Context
) : SharedPreferencesManager {

    private val sharedPreferences: SharedPreferences =
        applicationContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    private val sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferences.edit()

    override suspend fun putBoolean(value: Boolean) {
        sharedPreferencesEditor.putBoolean(BOOLEAN_THEME, value).commit()
    }

    override suspend fun putString(value: String) {
        sharedPreferencesEditor.putString(LAYOUT_MANAGER, value).commit()
    }

    override suspend fun getBoolean(): Boolean {
        return sharedPreferences.getBoolean(BOOLEAN_THEME, true)
    }

    override suspend fun getString(): String {
        return sharedPreferences.getString(LAYOUT_MANAGER, LINEAR) ?: LINEAR
    }

    companion object {
        const val SHARED_PREFERENCES_NAME = "NOTES_SHARED_PREFERENCES"
        const val BOOLEAN_THEME = "BOOLEAN_THEME"
        const val LAYOUT_MANAGER = "LAYOUT_MANAGER"
        const val LINEAR = "LINEAR"
        const val GRID = "GRID"
    }
}
