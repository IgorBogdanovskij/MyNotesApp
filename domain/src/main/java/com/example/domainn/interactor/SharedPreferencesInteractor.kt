package com.example.domainn.interactor

import com.example.domainn.sharedpref.SharedPreferencesManager
import javax.inject.Inject

interface SharedPreferencesInteractor {
    suspend fun putBoolean(value: Boolean)
    suspend fun checkLightTheme(): Boolean
}

class SharedPreferencesInteractorImpl @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager
) : SharedPreferencesInteractor {

    override suspend fun putBoolean(value: Boolean) {
        sharedPreferencesManager.putBoolean(value)
    }

    override suspend fun checkLightTheme(): Boolean {
        return sharedPreferencesManager.checkLightTheme()
    }
}
