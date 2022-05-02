package com.example.notes.features.edit.commands

import androidx.navigation.NavController
import com.example.notes.common.Command
import com.example.notes.databinding.FragmentEditBinding
import com.example.notes.features.edit.presentation.EditViewModel

class OnSaveEditCommand(
    private val navController: NavController,
    private val viewBinding: FragmentEditBinding,
    private val viewModel: EditViewModel
) : Command {

    override fun execute() {
        navController.popBackStack()
    }
}
