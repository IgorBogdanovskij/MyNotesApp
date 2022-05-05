package com.example.notes.features.details.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notes.R
import com.example.notes.databinding.FragmentNoteDetailsBinding
import com.example.notes.di.Injector
import com.example.notes.features.details.commands.ApplyChangesCommand
import com.example.notes.features.details.presentation.NoteDetailsViewModel
import com.example.notes.features.notes.ui.NotesFragment
import com.example.notes.models.NoteUi
import com.example.notes.utility.executeCommand
import javax.inject.Inject

class NoteDetailsFragment : Fragment(R.layout.fragment_note_details) {

    private var noteId: Int = 0
    private var noteUi: NoteUi? = null
    private lateinit var viewBinding: FragmentNoteDetailsBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<NoteDetailsViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Injector.inject(this)
        noteId = requireArguments().getInt(NotesFragment.NOTE_ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentNoteDetailsBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupData()
        initObservers()
        initListeners()
    }

    private fun initListeners() {
        viewBinding.applyChanges.setOnClickListener {
            executeCommand(
                ApplyChangesCommand(
                    noteId,
                    noteUi,
                    requireContext(),
                    findNavController(),
                    viewModel,
                    viewBinding
                )
            )
        }
    }

    //не успевает загрузиться заметка (ассинхронность)
    private fun setupData() {
        viewModel.getNoteById(noteId)
        viewModel.getAllNameOfGroups()
    }

    private fun initObservers() {
        viewModel.noteUi.observe(viewLifecycleOwner) {
            noteUi = it
            setupFragment()
        }

        viewModel.allNameOfGroups.observe(viewLifecycleOwner) {
            setListInAutoCompleteText(it.toList())
        }
    }

    private fun setupFragment() {
        viewBinding.editTextTitle.setText(noteUi?.title)
        viewBinding.editTextTextDescription.setText(noteUi?.description)

    }

    private fun setListInAutoCompleteText(list: List<String>) {
        val mArrayAdapter =
            ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, list);
        viewBinding.autoCompleteTextViewWrite.setAdapter(mArrayAdapter);

        list.find { noteUi?.group == it }?.let {
            viewBinding.autoCompleteTextViewWrite.setText(
                mArrayAdapter.getItem(mArrayAdapter.getPosition(it)), false
            )
        }
    }

    override fun onStop() {
        super.onStop()
        Injector.clearNoteDetailsComponent()
    }
}
