package com.example.notes.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.notes.R
import com.example.notes.features.notes.ui.OnSetSupportActionBarCallback

class MainActivity : AppCompatActivity(), OnSetSupportActionBarCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onEvent(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
    }
}
