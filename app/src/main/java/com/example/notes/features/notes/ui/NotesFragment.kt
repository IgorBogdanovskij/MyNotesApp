package com.example.notes.features.notes.ui

import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.core.view.allViews
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.common.Command
import com.example.notes.databinding.FragmentNotesBinding
import com.example.notes.databinding.GroupItemBinding
import com.example.notes.di.Injector
import com.example.notes.features.notes.commands.*
import com.example.notes.features.notes.presentation.NotesViewModel
import com.example.notes.features.notes.recycler.groups.GroupsAdapter
import com.example.notes.features.notes.recycler.groups.OnGroupClickListener
import com.example.notes.features.notes.recycler.notes.NotesAdapter
import com.example.notes.features.notes.recycler.notes.OnNoteClickListener
import com.example.notes.models.NoteUi
import javax.inject.Inject


class NotesFragment : Fragment(R.layout.fragment_notes),
    OnNoteClickListener,
    NotesItemTouchHelper.NotesItemTouchHelperCallback,
    PopupMenu.OnMenuItemClickListener,
    OnGroupClickListener {

    private var listCheckView = mutableListOf<View>()

    private lateinit var fragmentNotesBinding: FragmentNotesBinding
    lateinit var notesAdapter: NotesAdapter
    lateinit var adapterListGroups: GroupsAdapter

    private var popupMenu: PopupMenu? = null
    private lateinit var onSetSupportActionBarCallback: OnSetSupportActionBarCallback
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private var noteUi: NoteUi? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val notesViewModel by viewModels<NotesViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Injector.inject(this)
        onSetSupportActionBarCallback = context as OnSetSupportActionBarCallback
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
        onSetSupportActionBarCallback.onEvent(fragmentNotesBinding.toolbarNotes)

        setupNavigationView()
        setupNotesAdapter()
        setupGroupsAdapter()
        setupNotesRecyclerView()
        setupGroupsRecyclerView()
        initObservers()
        initListeners()
    }

    private fun initListeners() {
        fragmentNotesBinding.floatingButtonNotes.setOnClickListener {
            findNavController().navigate(R.id.action_notesFragment_to_notesFragmentWrite)
        }
        fragmentNotesBinding.includeDrawer.linearAllNote.setOnClickListener {
            executeCommand(
                ShowAllNotesCommand(
                    fragmentNotesBinding,
                    notesViewModel,
                    fragmentNotesBinding.includeDrawer.DrawerRecyclerView,
                    it
                )
            )
        }
    }

    private fun initObservers() {
        notesViewModel.allNotes.observe(
            viewLifecycleOwner,
            NotesUiHandler(fragmentNotesBinding, notesAdapter)::handle
        )
        notesViewModel.noteEntity.observe(viewLifecycleOwner) {
            noteUi = it
        }

        // TODO: think about move this feature in other place
        notesViewModel.allNotesByGroup.observe(viewLifecycleOwner) {
            notesAdapter.submitList(it.toList())
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
        fragmentNotesBinding.includeDrawer.DrawerRecyclerView.layoutManager =
            LinearLayoutManager(requireContext())
        fragmentNotesBinding.includeDrawer.DrawerRecyclerView.adapter = adapterListGroups
    }

    private fun setupNotesRecyclerView() {
        with(fragmentNotesBinding.notesRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = notesAdapter
        }
        NotesItemTouchHelper(this).also {
            ItemTouchHelper(it).attachToRecyclerView(fragmentNotesBinding.notesRecyclerView)
        }
    }

    private fun setupNavigationView() {
        navController = findNavController()
        appBarConfiguration =
            AppBarConfiguration(navController.graph, fragmentNotesBinding.drawerLayout)
        fragmentNotesBinding.toolbarNotes.setupWithNavController(navController, appBarConfiguration)
    }

    override fun onNoteClick(noteUi: NoteUi) {
        findNavController().navigate(
            R.id.noteDetailsFragment,
            bundleOf(EXTRA_ID to noteUi.id)
        )
    }

    override fun onNoteLongClick(view: View) {
        executeCommand(NoteLongClickCommand(popupMenu, view, requireContext()))
        popupMenu?.setOnMenuItemClickListener(this)
    }

    override fun onSwipe(position: Int) {
        executeCommand(SwipeToShowSnackBarCommand(notesAdapter, this, notesViewModel, position))
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
            PopupMenuItemClickCommand(item.itemId, findNavController(), notesViewModel, noteUi?.id)
        )
        return true
    }

    private fun executeCommand(command: Command) = command.execute()

    override fun onStop() {
        super.onStop()
        Injector.clearNotesComponent()
    }

    companion object {
        const val EXTRA_ID = "EXTRA_ID"
    }

    override fun onGroupClick(nameGroup: String, groupItemViewBinding: GroupItemBinding) {
        executeCommand(
            ShowNotesByGroupCommand(
                fragmentNotesBinding,
                groupItemViewBinding,
                notesViewModel,
                nameGroup
            )
        )
    }
}
