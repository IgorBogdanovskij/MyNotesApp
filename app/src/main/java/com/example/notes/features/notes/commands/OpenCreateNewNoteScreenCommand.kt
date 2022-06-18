package com.example.notes.features.notes.commands

import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.example.notes.R
import com.example.notes.app.OnSetupToolbarCallback
import com.example.notes.app.ToolbarSettings
import com.example.notes.common.Command
import com.example.notes.databinding.FragmentNotesBinding
import com.example.notes.features.notes.ui.NotesFragment
import com.example.core.extension.view.startAnimation
import com.example.core.extension.view.setVisibility

class OpenCreateNewNoteScreenCommand(
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
                    onStart = this@OpenCreateNewNoteScreenCommand::setupOnStart,
                    onEnd = this@OpenCreateNewNoteScreenCommand::setupOnEnd
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
        binding.notesRecyclerView.setVisibility(false)
        toolbarSettings?.let { onSetupToolbarCallback.onSetupToolbar(it.copy(isGone = true)) }
    }

    private fun setupOnEnd() {
        notesFragment.findNavController().navigate(R.id.action_notesFragment_to_notesFragmentWrite)
    }
}