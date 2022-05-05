package com.example.notes.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import com.example.notes.R
import com.example.notes.features.notes.ui.OnChangeTheme
import com.example.notes.features.notes.ui.OnSetSupportActionBarCallback

class MainActivity : AppCompatActivity(), OnSetSupportActionBarCallback, OnChangeTheme {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onEvent(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
    }

    override fun onChange(value: Boolean) {
        if (value) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}
