package com.example.notes.features.notes.commands

import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.example.notes.R
import com.example.core.base.OnSetupToolbarCallback
import com.example.core.model.ToolbarSettings
import com.example.notes.common.Command
import com.example.notes.databinding.FragmentNotesBinding
import com.example.notes.features.notes.ui.NotesFragment
import com.example.core.extension.view.startAnimation
import com.example.core.extension.view.setVisibility

class OpenDetailsScreenCommand(
    private val binding: FragmentNotesBinding,
    private val notesFragment: NotesFragment,
    private val toolbarSettings: ToolbarSettings?,
    private val onSetupToolbarCallback: OnSetupToolbarCallback
) : Command {

    override fun execute() {
        with(binding) {
            createNewNote.setVisibility(false)
            with(circleUnderCreateNewNote) {
                setVisibility(true)
                startAnimation(
                    animation = setupAnimation(),
                    onStart = this@OpenDetailsScreenCommand::setupOnStart,
                    onEnd = this@OpenDetailsScreenCommand::setupOnEnd
                )
            }
        }
    }

    private fun setupAnimation(): Animation {
        return AnimationUtils.loadAnimation(
            binding.root.context,
            R.anim.button_explosion_anim
        ).apply {
            duration = 600
            interpolator = AccelerateDecelerateInterpolator()
        }
    }

    private fun setupOnStart() {
        binding.emptyListItem.root.visibility = View.INVISIBLE
        binding.notesRecyclerView.setVisibility(false)
        toolbarSettings?.let { onSetupToolbarCallback.onSetupToolbar(it.copy(isGoneToolbar = true)) }
    }

    private fun setupOnEnd() {
        notesFragment.findNavController().navigate(R.id.noteDetailsFragment)
    }
}
