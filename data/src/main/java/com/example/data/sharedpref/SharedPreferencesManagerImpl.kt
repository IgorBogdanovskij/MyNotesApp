package com.example.data.sharedpref

import android.content.Context
import android.content.SharedPreferences
import com.example.domainn.sharedpref.SharedPreferencesManager
import javax.inject.Inject

class SharedPreferencesManagerImpl @Inject constructor(
    private val applicationContext: Context
) : SharedPreferencesManager {

    private val sharedPreferences: SharedPreferences =
        applicationContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    private val sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferences.edit()

    override suspend fun putBoolean(value: Boolean) {
        sharedPreferencesEditor.putBoolean(BOOLEAN_THEME, value).commit()
    }

    override suspend fun checkLightTheme(): Boolean {
        return sharedPreferences.getBoolean(BOOLEAN_THEME, true)
    }

    companion object {
        const val SHARED_PREFERENCES_NAME = "NOTES_SHARED_PREFERENCES"
        const val BOOLEAN_THEME = "BOOLEAN_THEME"

    }
}
