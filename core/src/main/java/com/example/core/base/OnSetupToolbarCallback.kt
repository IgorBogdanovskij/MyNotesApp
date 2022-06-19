package com.example.core.base

import com.example.core.model.ToolbarSettings

interface OnSetupToolbarCallback {
    fun onSetupToolbar(toolbarSettings: ToolbarSettings)
}