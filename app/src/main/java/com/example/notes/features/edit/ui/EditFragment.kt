package com.example.notes.features.edit.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notes.R
import com.example.notes.databinding.FragmentEditBinding
import com.example.notes.di.Injector
import com.example.notes.di.viewModel.ViewModelFactory
import com.example.notes.features.edit.commands.OnSaveEditCommand
import com.example.notes.features.edit.presentation.EditViewModel
import com.example.notes.features.notes.ui.NotesFragment
import com.example.notes.features.notes.ui.OnSetSupportActionBarCallback
import com.example.notes.models.NoteUi
import com.example.notes.utility.executeCommand
import javax.inject.Inject
import kotlin.properties.Delegates


class EditFragment : Fragment(R.layout.fragment_edit) {

    private lateinit var viewBinding: FragmentEditBinding
    private var noteId by Delegates.notNull<Int>()
    private lateinit var noteUi: NoteUi

    @Inject
    lateinit var factoryEditViewModel: ViewModelFactory
    val viewModel by viewModels<EditViewModel> { factoryEditViewModel }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        noteId = requireArguments().getInt(NotesFragment.NOTE_ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Injector.inject(this)
        viewBinding = FragmentEditBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        viewBinding.saveEditButton.setOnClickListener {
            executeCommand(
                OnSaveEditCommand(
                    findNavController(),
                    viewBinding,
                    viewModel
                )
            )
        }
    }

    private fun initData() {
        viewModel.getNoteById(noteId)
        viewModel.getAllNameOfGroups()
    }

    private fun initObservers() {
        viewModel.noteUi.observe(viewLifecycleOwner) { note ->
            noteUi = note
        }

        viewModel.allNameOfGroups.observe(viewLifecycleOwner) {
            setListInAutoCompleteText(it.toList())
        }
    }

    private fun setListInAutoCompleteText(list: List<String>) {
        val mArrayAdapter =
            ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, list);
        viewBinding.autoCompleteTextView.setAdapter(mArrayAdapter);

        list.find { noteUi.nameGroup == it }?.let {
            viewBinding.autoCompleteTextView.setText(
                mArrayAdapter.getItem(mArrayAdapter.getPosition(it)), false
            )
        }
    }

    override fun onStop() {
        super.onStop()
        Injector.clearEditComponent()
    }

    companion object {
        fun newInstance() = EditFragment()
    }
}
