package com.example.notes.features.notes.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.appcompat.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.base.BaseFragment
import com.example.core.base.OnChangeThemeCallback
import com.example.data.sharedPrefManager.SharedPreferencesManagerImpl.Companion.GRID
import com.example.data.sharedPrefManager.SharedPreferencesManagerImpl.Companion.LINEAR
import com.example.notes.R
import com.example.core.base.OnSetupNavigationViewCallback
import com.example.core.base.OnSetupToolbarCallback
import com.example.core.model.ToolbarSettings
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

class NotesFragment :
    BaseFragment<NotesViewModel, FragmentNotesBinding>(FragmentNotesBinding::inflate),
    OnNoteClickListener,
    NotesItemTouchHelper.NotesItemTouchHelperCallback,
    PopupMenu.OnMenuItemClickListener,
    OnGroupClickListener {

    companion object {
        const val NOTE_ID = "NOTE_ID"
    }

    private val notesAdapter = NotesAdapter(this)
    private val adapterListGroups = GroupsAdapter(this)

    private var popupMenu: PopupMenu? = null
    private var noteUi: NoteUi? = null

    private lateinit var onSetupToolbarCallback: OnSetupToolbarCallback
    private lateinit var onSetupNavigationViewCallback: OnSetupNavigationViewCallback
    private lateinit var onChangeThemeCallback: OnChangeThemeCallback

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override val viewModel: NotesViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Injector.inject(this)
        onSetupToolbarCallback = context as OnSetupToolbarCallback
        onSetupNavigationViewCallback = context as OnSetupNavigationViewCallback
        onChangeThemeCallback = context as OnChangeThemeCallback
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTheme(viewModel.checkLightTheme())
        setupNavigationView()
        setupNotesAdapter()
        setupGroupsAdapter()
        setupNotesRecyclerView(viewModel.checkLayoutManager())
        setupGroupsRecyclerView()
        initListeners()
    }

    private fun setupTheme(value: Boolean) {
        with(binding.includeDrawer.dayNightMode) {
            if (value) {
                setImageDrawable(getDrawable(requireContext(), R.drawable.ic_light_mode_black_24dp))
            } else {
                setImageDrawable(getDrawable(requireContext(), R.drawable.ic_mode_night_black_24dp))
            }
        }
        viewModel.putBoolean(value)
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
                        notesViewModel = viewModel,
                        filterType = NotesFilterType.ALLNOTES,
                        view = it
                    )
                )
            }
            includeDrawer.dayNightMode.setOnClickListener { setupTheme(!viewModel.checkLightTheme()) }
        }
    }

    private fun setupGroupsAdapter() = viewModel.getAllNameOfGroups()

    private fun setupNotesAdapter() = viewModel.getAllNotes()

    private fun setupGroupsRecyclerView() {
        with(binding.includeDrawer.drawerRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterListGroups
        }
    }

    private fun setupNotesRecyclerView(typeOfLayoutManager: String): Drawable? {
        viewModel.putString(typeOfLayoutManager)
        NotesItemTouchHelper(this).also {
            ItemTouchHelper(it).attachToRecyclerView(binding.notesRecyclerView)
        }
        return with(binding.notesRecyclerView) {
            adapter = notesAdapter
            when (typeOfLayoutManager) {
                LINEAR -> {
                    layoutManager = LinearLayoutManager(requireContext())
                    getDrawable(requireContext(), R.drawable.ic_rows)
                }
                GRID -> {
                    layoutManager = GridLayoutManager(requireContext(), 2)
                    getDrawable(requireContext(), R.drawable.ic_grid)
                }
                else -> null
            }
        }
    }

    private fun setupNavigationView() {
        onSetupNavigationViewCallback.onSetupNavigationView(binding.drawerLayout) { findNavController() }
    }

    override fun onNoteClick(noteUi: NoteUi) {
        findNavController().navigate(R.id.noteDetailsFragment, bundleOf(NOTE_ID to noteUi.id))
    }

    override fun onNoteLongClick(view: View, noteUi: NoteUi) {
        this.noteUi = noteUi
        executeCommand(NoteLongClickCommand(popupMenu, view, requireContext(), this))
    }

    override fun onSwipe(position: Int) {
        executeCommand(DeleteNoteBySwipeCommand(notesAdapter, this, viewModel, position))
    }

    override fun onMove(viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) {
        executeCommand(
            MoveToChangeNotePositionCommand(viewHolder, target, popupMenu, notesAdapter, viewModel)
        )
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        executeCommand(
            PopupMenuItemClickCommand(item.itemId, findNavController(), viewModel, noteUi, this)
        )
        return true
    }

    override fun onGroupClick(
        nameGroup: String,
        view: View
    ) {
        executeCommand(
            ShowNotesByFilterCommand(
                notesBinding = binding,
                notesViewModel = viewModel,
                filterType = NotesFilterType.NOTESBYGROUP,
                nameGroup = nameGroup,
                view = view
            )
        )
    }

    override fun onBindViewModel(viewModel: NotesViewModel) {
        with(viewModel) {
            allNotes.observe(viewLifecycleOwner, NotesUiHandler(binding, notesAdapter)::handle)
            allNotesByGroup.observe(viewLifecycleOwner) { notesAdapter.submitList(it.toList()) }
            noteUi.observe(viewLifecycleOwner) { this@NotesFragment.noteUi = it }
            allNameOfGroups.observe(viewLifecycleOwner) { adapterListGroups.submitList(it.toList()) }
        }
    }

    override fun onBindToolbar(settings: ToolbarSettings) {
        toolbarSettings = settings.copy(
            title = getString(R.string.notesFragmentToolbarTitle),
            isGone = false,
            backButtonVisibility = false,
            onChangeLayoutManagerListener = {
                when (viewModel.checkLayoutManager()) {
                    LINEAR -> setupNotesRecyclerView(GRID)
                    GRID -> setupNotesRecyclerView(LINEAR)
                    else -> null
                }
            }
        )
    }

    override fun onStop() {
        super.onStop()
        Injector.clearNotesComponent()
    }
}
