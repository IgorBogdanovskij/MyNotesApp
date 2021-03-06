package com.example.notes.features.notes.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
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
    OnGroupClickListener {

    companion object {
        const val NOTE_ID = "NOTE_ID"
    }

    private val notesAdapter = NotesAdapter(this)
    private val selectionManager: SelectionManager = SimpleSelectionManager(notesAdapter)
    private val adapterListGroups = GroupsAdapter(this)

    var noteUi: NoteUi? = null

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
        setupNavigationView()
        setupTheme(viewModel.checkLightTheme())
        setupNotesAdapter()
        setupGroupsAdapter()
        setupNotesRecyclerView(viewModel.checkLayoutManager())
        setupGroupsRecyclerView()
        initListeners()
    }

    private fun setupTheme(isLight: Boolean) {
        with(binding.includeDrawer.dayNightMode) {
            if (isLight) {
                setImageDrawable(getDrawable(requireContext(), R.drawable.ic_light_mode_black_24dp))
            } else {
                setImageDrawable(getDrawable(requireContext(), R.drawable.ic_mode_night_black_24dp))
            }
        }
        viewModel.putBoolean(isLight)
        onChangeThemeCallback.onChange(isLight)
    }

    private fun initListeners() {
        with(binding) {
            createNewNote.setOnClickListener {
                executeCommand(
                    OpenDetailsScreenCommand(
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

    fun setupNavigationView() {
        onSetupNavigationViewCallback.onSetupNavigationView(binding.drawerLayout) { findNavController() }
    }

    override fun onNoteClick(noteUi: NoteUi) {
        if (toolbarSettings.isSelectionModeActive) {
            executeCommand(
                SelectionClickCommand(
                    noteUi = noteUi,
                    binding = binding,
                    notesViewModel = viewModel,
                    selectionManager = selectionManager,
                    notesFragment = this,
                    onSetupToolbarCallback = onSetupToolbarCallback
                )
            )
        } else {
            findNavController().navigate(
                R.id.action_notesFragment_to_noteDetailsFragment,
                bundleOf(NOTE_ID to noteUi.id)
            )
        }
    }

    override fun onNoteLongClick(view: View, noteUi: NoteUi) {
        executeCommand(
            SelectionClickCommand(
                noteUi = noteUi,
                binding = binding,
                notesViewModel = viewModel,
                selectionManager = selectionManager,
                notesFragment = this,
                onSetupToolbarCallback = onSetupToolbarCallback
            )
        )
    }

    override fun onSwipe(position: Int) {
        executeCommand(DeleteNoteBySwipeCommand(notesAdapter, this, viewModel, position))
    }

    override fun onMoved(viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) {
        executeCommand(SwapNotesInNotesRecyclerCommand(viewHolder, target, notesAdapter))
    }

    override fun onEndAction(fromAdapterPosition: Int, toAdapterPosition: Int) {
        executeCommand(
            SaveSwapToDataBaseCommand(
                notesViewModel = viewModel,
                fromAdapterPosition = fromAdapterPosition,
                toAdapterPosition = toAdapterPosition,
                notesAdapter = notesAdapter
            )
        )
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
            state.observe(viewLifecycleOwner, NotesUiHandler(binding, notesAdapter)::handle)
            allNotesByGroup.observe(viewLifecycleOwner) { notesAdapter.submitList(it.toList()) }
            noteUi.observe(viewLifecycleOwner) { this@NotesFragment.noteUi = it }
            allNameOfGroups.observe(viewLifecycleOwner) { adapterListGroups.submitList(it.toList()) }
        }
    }

    override fun onBindToolbar(settings: ToolbarSettings) {
        toolbarSettings = settings.copy(
            title = getString(R.string.notesFragmentToolbarTitle),
            isGoneToolbar = false,
            isBackButtonVisible = false,
            isChangeLayoutIcon = true,
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
