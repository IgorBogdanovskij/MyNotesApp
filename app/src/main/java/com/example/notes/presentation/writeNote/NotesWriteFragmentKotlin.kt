package com.example.notes.presentation.writeNote

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.notes.App
import com.example.notes.R
import com.example.notes.data.locale.entity.NoteEntityKotlin
import com.example.notes.databinding.FragmentNotesWriteBinding
import com.example.notes.presentation.listNotes.ListNotesFragmentKotlin
import java.util.*
import javax.inject.Inject

class NotesWriteFragmentKotlin : Fragment(R.layout.fragment_notes_write) {

    private var list = listOf<String>()

    private lateinit var viewBinding: FragmentNotesWriteBinding

    private var noteId: Int = 0

    private var noteEntityKotlin: NoteEntityKotlin? = null

    @Inject
    lateinit var viewModel: NotesWriteViewModelKotlin

    override fun onAttach(context: Context) {
        super.onAttach(context)

        noteId = requireArguments().getInt(ListNotesFragmentKotlin.EXTRA_ID, 0)
    }

    private fun initDagger() {
        (activity?.application as App).appComponent.noteWriteComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initDagger()
        viewBinding = FragmentNotesWriteBinding.inflate(layoutInflater)

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllNameOfGroups()

        viewModel.getNoteById(noteId)

        viewModel.noteEntityKotlin.observe(viewLifecycleOwner) {
            noteEntityKotlin = it
            setupFragment()
        }

        viewModel.allNameOfGroups.observe(viewLifecycleOwner) {
            list = it.toList()
            setListInAutoCompleteText()
        }

        viewBinding.floatingActionButtonAdd.setOnClickListener {
            if (noteId != 0) {
                updateNote()
                findNavController().popBackStack()
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
        viewBinding.editTextTitle.setText(noteEntityKotlin?.title)
        viewBinding.editTextTextDescription.setText(noteEntityKotlin?.description)
    }

    private fun updateNote() {

        val noteUpdated = noteEntityKotlin

        if (viewBinding.autoCompleteTextViewWrite.text.toString().isNotEmpty()) {
            noteUpdated?.nameGroup = viewBinding.autoCompleteTextViewWrite.text.toString()
            viewModel.updateNote(noteUpdated!!)
        }

        if (noteEntityKotlin?.title?.lowercase() != viewBinding.editTextTitle.text.toString()
                .lowercase() ||
            noteEntityKotlin?.description?.lowercase() != viewBinding.editTextTextDescription.text.toString()
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
                NoteEntityKotlin(
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

    private fun setListInAutoCompleteText() {
        val mArrayAdapter =
            ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, list);
        viewBinding.autoCompleteTextViewWrite.setAdapter(mArrayAdapter);
    }
}