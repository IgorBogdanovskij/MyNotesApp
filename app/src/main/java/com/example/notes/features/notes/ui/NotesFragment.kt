package com.example.notes.features.notes.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.sharedPrefManager.SharedPreferencesManagerImpl.Companion.GRID
import com.example.data.sharedPrefManager.SharedPreferencesManagerImpl.Companion.LINEAR
import com.example.notes.R
import com.example.notes.app.OnSetupNavigationViewCallback
import com.example.notes.app.OnSetupToolbarCallback
import com.example.notes.app.ToolbarSettings
import com.example.notes.databinding.FragmentNotesBinding
import com.example.notes.di.Injector
import com.example.notes.features.notes.commands.*
import com.example.notes.features.notes.model.NotesFilterType
import com.example.notes.features.notes.presentation.NotesViewModel
import com.example.notes.features.notes.recycler.groups.GroupsAdapter
import com.example.notes.features.notes.recycler.groups.OnGroupClickListener
import com.example.notes.features.notes.recycler.notes.NotesAdapter
import com.example.notes.features.notes.recycler.notes.OnNoteClickListener
import com.example.notes.models.NoteUi
import com.example.notes.utility.executeCommand
import javax.inject.Inject

class NotesFragment : Fragment(R.layout.fragment_notes),
    OnNoteClickListener,
    NotesItemTouchHelper.NotesItemTouchHelperCallback,
    PopupMenu.OnMenuItemClickListener,
    OnGroupClickListener {

    private lateinit var binding: FragmentNotesBinding
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var adapterListGroups: GroupsAdapter

    private var popupMenu: PopupMenu? = null
    private var noteUi: NoteUi? = null

    private lateinit var onSetupToolbarCallback: OnSetupToolbarCallback
    private lateinit var onSetupNavigationViewCallback: OnSetupNavigationViewCallback
    private lateinit var onChangeThemeCallback: OnChangeThemeCallback

    private var toolbarSettings: ToolbarSettings? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val notesViewModel by viewModels<NotesViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Injector.inject(this)
        onSetupToolbarCallback = context as OnSetupToolbarCallback
        onSetupNavigationViewCallback = context as OnSetupNavigationViewCallback
        onChangeThemeCallback = context as OnChangeThemeCallback
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initToolbar()
        return FragmentNotesBinding.inflate(layoutInflater).also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTheme(notesViewModel.checkLightTheme())
        setupNavigationView()
        setupNotesAdapter()
        setupGroupsAdapter()
        setupNotesRecyclerView(notesViewModel.checkLayoutManager())
        setupGroupsRecyclerView()
        initObservers()
        initListeners()
    }

    private fun setupTheme(value: Boolean) {
        if (value) {
            binding.includeDrawer.dayNightMode.setImageDrawable(
                AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.ic_light_mode_black_24dp
                )
            )
        } else {
            binding.includeDrawer.dayNightMode.setImageDrawable(
                AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.ic_mode_night_black_24dp
                )
            )
        }
        notesViewModel.putBoolean(value)
        onChangeThemeCallback.onChange(value)
    }

    private fun initListeners() {
        with(binding) {
            createNewNote.setOnClickListener {
                executeCommand(
                    OpenCreateNewNoteScreenCommand(
                        binding = binding,
                        notesFragment = this@NotesFragment,
                        toolbarSettings = toolbarSettings,
                        onSetupToolbarCallback = onSetupToolbarCallback
                    )
                )
            }
            includeDrawer.linearAllNote.setOnClickListener {
                executeCommand(
                    ShowNotesByFilterCommand(
                        notesBinding = binding,
                        notesViewModel = notesViewModel,
                        filterType = NotesFilterType.ALLNOTES,
                        view = it
                    )
                )
            }
            includeDrawer.dayNightMode.setOnClickListener {
                setupTheme(!notesViewModel.checkLightTheme())
            }
        }
    }

    private fun initToolbar() {
        toolbarSettings = ToolbarSettings(
            title = getString(R.string.notesFragmentToolbarTitle),
            isGone = false,
            backButtonVisibility = false,
            onChangeLayoutManagerListener = {
                when (notesViewModel.checkLayoutManager()) {
                    LINEAR -> setupNotesRecyclerView(GRID)
                    GRID -> setupNotesRecyclerView(LINEAR)
                    else -> null
                }
            }
        ).apply { onSetupToolbarCallback.onSetupToolbar(this) }
    }

    private fun initObservers() {
        notesViewModel.allNotes.observe(
            viewLifecycleOwner,
            NotesUiHandler(binding, notesAdapter)::handle
        )

        notesViewModel.allNotesByGroup.observe(viewLifecycleOwner) {
            notesAdapter.submitList(it.toList())
        }

        notesViewModel.noteUi.observe(viewLifecycleOwner) {
            noteUi = it
        }

        notesViewModel.allNameOfGroups.observe(viewLifecycleOwner) {
            adapterListGroups.submitList(it.toList())
        }
    }

    private fun setupGroupsAdapter() {
        notesViewModel.getAllNameOfGroups()
        adapterListGroups = GroupsAdapter(this)
    }

    private fun setupNotesAdapter() {
        notesViewModel.getAllNotes()
        notesAdapter = NotesAdapter(this)
    }

    private fun setupGroupsRecyclerView() {
        binding.includeDrawer.drawerRecyclerView.layoutManager =
            LinearLayoutManager(requireContext())
        binding.includeDrawer.drawerRecyclerView.adapter = adapterListGroups
    }

    private fun setupNotesRecyclerView(typeOfLayoutManager: String): Drawable? {
        notesViewModel.putString(typeOfLayoutManager)
        NotesItemTouchHelper(this).also {
            ItemTouchHelper(it).attachToRecyclerView(binding.notesRecyclerView)
        }
        return with(binding.notesRecyclerView) {
            adapter = notesAdapter
            when (typeOfLayoutManager) {
                LINEAR -> {
                    layoutManager = LinearLayoutManager(requireContext())
                    AppCompatResources.getDrawable(requireContext(), R.drawable.ic_rows)
                }
                GRID -> {
                    layoutManager = GridLayoutManager(requireContext(), 2)
                    AppCompatResources.getDrawable(requireContext(), R.drawable.ic_grid)
                }
                else -> null
            }
        }
    }

    private fun setupNavigationView() {
        onSetupNavigationViewCallback.onSetupNavigationView(binding.drawerLayout, this)
    }

    override fun onNoteClick(noteUi: NoteUi) {
        findNavController().navigate(R.id.noteDetailsFragment, bundleOf(NOTE_ID to noteUi.id))
    }

    override fun onNoteLongClick(view: View, noteUi: NoteUi) {
        this.noteUi = noteUi
        executeCommand(NoteLongClickCommand(popupMenu, view, requireContext(), this))
    }

    override fun onSwipe(position: Int) {
        executeCommand(DeleteNoteBySwipeCommand(notesAdapter, this, notesViewModel, position))
    }

    override fun onMove(viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) {
        executeCommand(
            MoveToChangeNotePositionCommand(
                viewHolder,
                target,
                popupMenu,
                notesAdapter,
                notesViewModel
            )
        )
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        executeCommand(
            PopupMenuItemClickCommand(
                item.itemId,
                findNavController(),
                notesViewModel,
                noteUi,
                this
            )
        )
        return true
    }

    override fun onStop() {
        super.onStop()
        Injector.clearNotesComponent()
    }

    override fun onGroupClick(
        nameGroup: String,
        view: View
    ) {
        executeCommand(
            ShowNotesByFilterCommand(
                notesBinding = binding,
                notesViewModel = notesViewModel,
                filterType = NotesFilterType.NOTESBYGROUP,
                nameGroup = nameGroup,
                view = view
            )
        )
    }

    companion object {
        const val NOTE_ID = "NOTE_ID"
    }
}
