package com.example.core.model

import android.graphics.drawable.Drawable
import com.example.core.R

data class ToolbarSettings(
    val title: String = "",
    val isGoneToolbar: Boolean = false,
    val isBackButtonVisible: Boolean = false,
    val backButtonIcon: Int = R.drawable.ic_arrow_back_white,
    val isChangeLayoutIcon: Boolean = false,
    val isSelectionModeActive: Boolean = false,
    val titleInSelectionMode: String = "",
    val onChangeLayoutManagerListener: () -> Drawable? = { null },
    val onCancelButtonListener: () -> Unit = {},
    val onDeleteIconListener: () -> Unit = {},
)
