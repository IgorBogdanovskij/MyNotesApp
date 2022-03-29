package com.example.notes.features.notedetails_feature

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notes.app.App
import com.example.notes.R
import com.example.domainn.entity.NoteEntity
import com.example.notes.databinding.FragmentNoteDetailsBinding
import com.example.notes.features.notes_feature.NotesFragment
import java.util.*
import javax.inject.Inject

class NoteDetailsFragment : Fragment(R.layout.fragment_note_details) {

    private lateinit var viewBinding: FragmentNoteDetailsBinding

    private var noteId: Int = 0

    private var noteEntity: NoteEntity? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<NoteDetailsViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        noteId = requireArguments().getInt(NotesFragment.EXTRA_ID, 0)
    }

    private fun initDagger() {
        (activity?.application as App)
            .appComponent
            .plusNoteDetailsComponent()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initDagger()
        viewBinding = FragmentNoteDetailsBinding.inflate(inflater)

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllNameOfGroups()

        viewModel.getNoteById(noteId)

        viewModel.noteEntity.observe(viewLifecycleOwner) {
            noteEntity = it
            setupFragment()
        }

        viewModel.allNameOfGroups.observe(viewLifecycleOwner) {
            setListInAutoCompleteText(it.toList())
        }

        viewBinding.floatingActionButtonAdd.setOnClickListener {
            if (noteId != 0) {
                updateNote()
                findNavController().navigate(R.id.action_notesFragmentWrite_to_listNotesFragment)
            } else {
                if (viewBinding.editTextTitle.text.toString().isEmpty() &&
                    viewBinding.editTextTextDescription.text.toString().isEmpty() &&
                    viewBinding.autoCompleteTextViewWrite.text.isEmpty()
                ) {
                    Toast.makeText(requireContext(), "The note is empty", Toast.LENGTH_SHORT).show()
                } else {
                    createNote()
                    findNavController().popBackStack()
                }
            }

        }
    }

    private fun setupFragment() {
        viewBinding.editTextTitle.setText(noteEntity?.title)
        viewBinding.editTextTextDescription.setText(noteEntity?.description)
    }

    private fun updateNote() {

        val noteUpdated = noteEntity

        if (viewBinding.autoCompleteTextViewWrite.text.toString().isNotEmpty()) {
            noteUpdated?.nameGroup = viewBinding.autoCompleteTextViewWrite.text.toString()
            viewModel.updateNote(noteUpdated!!)
        }

        if (noteEntity?.title?.lowercase() != viewBinding.editTextTitle.text.toString()
                .lowercase() ||
            noteEntity?.description?.lowercase() != viewBinding.editTextTextDescription.text.toString()
                .lowercase()
        ) {
            noteUpdated?.title = viewBinding.editTextTitle.text.toString()
            noteUpdated?.description = viewBinding.editTextTextDescription.text.toString()

            viewModel.updateNote(noteUpdated!!)
        }
    }

    private fun createNote() {

        var nameGroup = ""

        if (viewBinding.editTextTitle.text.toString().isNotEmpty() &&
            viewBinding.editTextTextDescription.text.toString().isNotEmpty()
        ) {
            if (viewBinding.autoCompleteTextViewWrite.text.toString().isNotEmpty()) {
                nameGroup = viewBinding.autoCompleteTextViewWrite.text.toString()
            }
            viewModel.addNote(
                NoteEntity(
                    viewBinding.editTextTitle.text.toString(),
                    viewBinding.editTextTextDescription.text.toString(),
                    0,
                    0,
                    Date(),
                    nameGroup
                )
            )
        }

    }

    private fun setListInAutoCompleteText(list: List<String>) {
        val mArrayAdapter =
            ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, list);
        viewBinding.autoCompleteTextViewWrite.setAdapter(mArrayAdapter);
    }
}