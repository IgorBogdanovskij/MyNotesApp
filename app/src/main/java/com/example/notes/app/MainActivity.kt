package com.example.notes.app

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.features.notes.ui.NotesFragment
import com.example.notes.features.notes.ui.OnChangeThemeCallback
import com.example.core.extension.view.setGone

class MainActivity :
    AppCompatActivity(),
    OnChangeThemeCallback,
    OnSetupToolbarCallback,
    OnSetupNavigationViewCallback {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onChange(value: Boolean) {
        if (value) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    override fun onSetupToolbar(toolbarSettings: ToolbarSettings) {
        binding.includeAppBar.appBar.setGone(toolbarSettings.isGone)
        binding.includeAppBar.changeLayoutManager.setOnClickListener {
            (it as ImageView).setImageDrawable(toolbarSettings.onChangeLayoutManagerListener())
        }
        with(binding.includeAppBar.toolbar) {
            title = toolbarSettings.title
            if (!toolbarSettings.backButtonVisibility) navigationIcon = null
        }
    }

    override fun onSetupNavigationView(drawerLayout: DrawerLayout, fragment: NotesFragment) {
        val navController = fragment.findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        binding.includeAppBar.toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}
