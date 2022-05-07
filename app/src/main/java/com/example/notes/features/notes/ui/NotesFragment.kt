package com.example.notes.features.notes.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.sharedpref.SharedPreferencesManagerImpl.Companion.GRID
import com.example.data.sharedpref.SharedPreferencesManagerImpl.Companion.LINEAR
import com.example.notes.R
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

    private lateinit var fragmentNotesBinding: FragmentNotesBinding
    lateinit var notesAdapter: NotesAdapter
    lateinit var adapterListGroups: GroupsAdapter

    private var popupMenu: PopupMenu? = null
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private var noteUi: NoteUi? = null

    private lateinit var onSetSupportActionBarCallback: OnSetSupportActionBarCallback
    private lateinit var onChangeTheme: OnChangeTheme

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val notesViewModel by viewModels<NotesViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Injector.inject(this)
        onSetSupportActionBarCallback = context as OnSetSupportActionBarCallback
        onChangeTheme = context as OnChangeTheme
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentNotesBinding.inflate(layoutInflater).also { fragmentNotesBinding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onSetSupportActionBarCallback.onEvent(fragmentNotesBinding.toolbarNotesScreen)

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
            fragmentNotesBinding.includeDrawer.dayNightMode.setImageDrawable(
                AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.ic_light_mode_black_24dp
                )
            )
        } else {
            fragmentNotesBinding.includeDrawer.dayNightMode.setImageDrawable(
                AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.ic_mode_night_black_24dp
                )
            )
        }
        notesViewModel.putBoolean(value)
        onChangeTheme.onChange(value)
    }

    private fun initListeners() {
        fragmentNotesBinding.floatingButtonNotes.setOnClickListener {
            findNavController().navigate(R.id.action_notesFragment_to_notesFragmentWrite)
        }
        fragmentNotesBinding.includeDrawer.linearAllNote.setOnClickListener {
            executeCommand(
                ShowNotesByFilterCommand(
                    notesBinding = fragmentNotesBinding,
                    notesViewModel = notesViewModel,
                    filterType = NotesFilterType.ALLNOTES,
                    view = it
                )
            )
        }
        fragmentNotesBinding.includeDrawer.dayNightMode.setOnClickListener {
            setupTheme(!notesViewModel.checkLightTheme())
        }
        fragmentNotesBinding.changeLayoutManager.setOnClickListener {
            when (notesViewModel.checkLayoutManager()) {
                LINEAR -> setupNotesRecyclerView(GRID)
                GRID -> setupNotesRecyclerView(LINEAR)
            }
        }
    }

    private fun initObservers() {
        notesViewModel.allNotes.observe(
            viewLifecycleOwner,
            NotesUiHandler(fragmentNotesBinding, notesAdapter)::handle
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
        fragmentNotesBinding.includeDrawer.drawerRecyclerView.layoutManager =
            LinearLayoutManager(requireContext())
        fragmentNotesBinding.includeDrawer.drawerRecyclerView.adapter = adapterListGroups
    }

    private fun setupNotesRecyclerView(typeOfLayoutManager: String) {
        with(fragmentNotesBinding.notesRecyclerView) {
            when (typeOfLayoutManager) {
                LINEAR -> {
                    fragmentNotesBinding.changeLayoutManager.setImageDrawable(
                        AppCompatResources.getDrawable(requireContext(), R.drawable.ic_rows)
                    )
                    layoutManager = LinearLayoutManager(requireContext())
                }
                GRID -> {
                    fragmentNotesBinding.changeLayoutManager.setImageDrawable(
                        AppCompatResources.getDrawable(requireContext(), R.drawable.ic_grid)
                    )
                    layoutManager = GridLayoutManager(requireContext(), 2)
                }
            }
            adapter = notesAdapter
            notesViewModel.putString(typeOfLayoutManager)
        }
        NotesItemTouchHelper(this).also {
            ItemTouchHelper(it).attachToRecyclerView(fragmentNotesBinding.notesRecyclerView)
        }
    }

    private fun setupNavigationView() {
        navController = findNavController()
        appBarConfiguration =
            AppBarConfiguration(navController.graph, fragmentNotesBinding.drawerLayout)
        fragmentNotesBinding.toolbarNotesScreen.setupWithNavController(
            navController,
            appBarConfiguration
        )
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
                notesBinding = fragmentNotesBinding,
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
