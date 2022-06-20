package com.example.notes.features.notes.commands

import androidx.appcompat.widget.PopupMenu
import com.example.notes.R
import com.example.notes.common.Command

class NoteLongClickCommand(
    private var popupMenu: PopupMenu?,
    private val listener: PopupMenu.OnMenuItemClickListener,
) : Command {

    override fun execute() {
        popupMenu?.setOnMenuItemClickListener(listener)
        popupMenu?.menuInflater?.inflate(R.menu.popup_menu, popupMenu!!.menu)
        popupMenu?.show()
    }
}
