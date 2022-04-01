package com.example.notes.features.notes.commands

import android.content.Context
import android.view.View
import com.example.notes.common.Command
import androidx.appcompat.widget.PopupMenu
import com.example.notes.R

class NoteLongClickCommand(
    private var popupMenu: PopupMenu?,
    private val view: View,
    private val context: Context
) : Command {

    override fun execute() {
        popupMenu = PopupMenu(context, view)
        popupMenu?.menuInflater?.inflate(
            R.menu.popup_menu_drawer,
            popupMenu!!.menu
        )
        popupMenu?.show()
    }
}
