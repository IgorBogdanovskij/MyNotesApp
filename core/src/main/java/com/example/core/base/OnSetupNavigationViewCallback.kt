package com.example.core.base

import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController

interface OnSetupNavigationViewCallback {
    fun onSetupNavigationView(drawerLayout: DrawerLayout, block: () -> NavController)
}
