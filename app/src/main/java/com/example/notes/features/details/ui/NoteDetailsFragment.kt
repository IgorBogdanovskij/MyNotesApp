package com.example.notes.features.details.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.core.base.BaseFragment
import com.example.core.extension.view.setGone
import com.example.core.model.ToolbarSettings
import com.example.notes.R
import com.example.notes.databinding.FragmentNoteDetailsBinding
import com.example.notes.di.Injector
import com.example.notes.features.details.commands.ApplyChangesCommand
import com.example.notes.features.details.presentation.NoteDetailsViewModel
import com.example.notes.features.notes.ui.NotesFragment
import com.example.notes.models.NoteUi
import com.example.notes.utility.executeCommand
import javax.inject.Inject

class NoteDetailsFragment :
    BaseFragment<NoteDetailsViewModel, FragmentNoteDetailsBinding>(FragmentNoteDetailsBinding::inflate) {

    private var noteUi: NoteUi? = null
    private val noteId: Int by lazy { requireArguments().getInt(NotesFragment.NOTE_ID) }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override val viewModel by viewModels<NoteDetailsViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Injector.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupData()
        initListeners()
        renderUi(noteUi)
    }

    private fun initListeners() {
        with(binding) {
            applyChanges.setOnClickListener {
                executeCommand(
                    ApplyChangesCommand(
                        noteId = noteId,
                        noteUi = noteUi,
                        navController = findNavController(),
                        viewModel = viewModel,
                        binding = binding
                    )
                )
            }
            editNote.setOnClickListener {
                findNavController().navigate(
                    R.id.editFragment,
                    bundleOf(NotesFragment.NOTE_ID to noteUi!!.id)
                )
            }
        }
    }

    private fun setupData() {
        viewModel.getNoteById(noteId)
        viewModel.getAllNameOfGroups()
    }

    private fun setListInAutoCompleteText(list: List<String>) {
        val mArrayAdapter =
            ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, list);
        binding.autoCompleteTextViewWrite.setAdapter(mArrayAdapter);

        list.find { noteUi?.group == it }?.let {
            binding.autoCompleteTextViewWrite.setText(
                mArrayAdapter.getItem(mArrayAdapter.getPosition(it)), false
            )
        }
    }

    private fun renderUi(noteUi: NoteUi?) {
        if (noteUi != null) {
            this.noteUi = noteUi
            binding.editTextTitle.setText(noteUi.title)
            binding.editTextTextDescription.setText(noteUi.description)
            binding.editNote.setGone(false)
        } else {
            binding.editNote.setGone(true)
        }
    }

    override fun onBindViewModel(viewModel: NoteDetailsViewModel) {
        viewModel.noteUi.observe(viewLifecycleOwner, this::renderUi)
        viewModel.allNameOfGroups.observe(viewLifecycleOwner) { setListInAutoCompleteText(it.toList()) }
    }

    override fun onBindToolbar(settings: ToolbarSettings) {
        toolbarSettings = settings.copy(
            isBackButtonVisible = true,
            isChangeLayoutIcon = false,
            title = resources.getString(R.string.details_screen)
        )
    }

    override fun onStop() {
        super.onStop()
        Injector.clearNoteDetailsComponent()
    }
}
