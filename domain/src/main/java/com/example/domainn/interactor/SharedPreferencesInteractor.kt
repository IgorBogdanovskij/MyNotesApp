package com.example.domainn.interactor

import android.util.Log
import com.example.domainn.sharedpref.SharedPreferencesManager
import javax.inject.Inject

interface SharedPreferencesInteractor {
    suspend fun putBoolean(value: Boolean)
    suspend fun putString(value: String)
    suspend fun checkLightTheme(): Boolean
    suspend fun checkLayoutManager(): String
}

class SharedPreferencesInteractorImpl @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager
) : SharedPreferencesInteractor {

    override suspend fun putBoolean(value: Boolean) {
        sharedPreferencesManager.putBoolean(value)
    }

    override suspend fun putString(value: String) {
        sharedPreferencesManager.putString(value)
    }

    override suspend fun checkLightTheme(): Boolean {
        return sharedPreferencesManager.checkLightTheme()
    }

    override suspend fun checkLayoutManager(): String {
        return sharedPreferencesManager.checkLayoutManager()
    }
}
