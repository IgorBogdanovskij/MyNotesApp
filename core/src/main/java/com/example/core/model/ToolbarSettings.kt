package com.example.core.model

import android.graphics.drawable.Drawable

data class ToolbarSettings(
    val title: String = "",
    val isGone: Boolean = false,
    val backButtonVisibility: Boolean = false,
    val onChangeLayoutManagerListener: () -> Drawable? = { null },
)
