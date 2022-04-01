package com.example.notes.app

import android.app.Application
import com.example.notes.di.Injector

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.initAppComponent(this)
    }
}
