package com.example.notes.app

import androidx.drawerlayout.widget.DrawerLayout
import com.example.notes.features.notes.ui.NotesFragment

interface OnSetupNavigationViewCallback {
    fun onSetupNavigationView(drawerLayout: DrawerLayout, fragment: NotesFragment)
}
