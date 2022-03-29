package com.example.notes.features.notes_feature

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
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
import com.example.notes.app.App
import com.example.notes.R
import com.example.notes.databinding.FragmentNotesBinding
import com.example.notes.mappers.mapToNoteEntity
import com.example.notes.models.NoteUI
import com.example.notes.features.groupsnotes_feature.recycler.NavigationViewDrawer
import com.example.notes.features.notes_feature.recycler.NotesAdapter
import com.example.notes.features.notes_feature.state.State
import com.example.notes.features.notes_feature.touchHelper.NotesItemTouchHelperKotlin
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class NotesFragment : Fragment(R.layout.fragment_notes) {

    private var isCheckedCategory = false

    private var checkedView: View? = null

    private var listCheckView = mutableListOf<View>()

    lateinit var viewBinding: FragmentNotesBinding

    lateinit var adapterListNotes: NotesAdapter

    lateinit var adapterListGroups: NavigationViewDrawer

    private var popupMenu: PopupMenu? = null

    private lateinit var onCallbackSupportActionBar: CallbackSupportActionBar

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var navController: NavController

    private var noteUI: NoteUI? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<NotesViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        onCallbackSupportActionBar = context as CallbackSupportActionBar
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initDagger()
        viewBinding = FragmentNotesBinding.inflate(layoutInflater)

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigationView()

        setupNotesAdapter()

        setupListGroupAdapter()

        setupRecyclerView()

        setupDrawerRecyclerView()

        initObservers()

        initListeners()

        if (isCheckedCategory) {
            checkedView?.setBackgroundResource(R.drawable.select_item)
        }
    }

    private fun initListeners() {
        viewBinding.floatingButtonNotes.setOnClickListener {
            findNavController().navigate(R.id.action_notesFragment_to_notesFragmentWrite)
        }

        viewBinding.includeDrawer.linearAllNote.setOnClickListener {

            checkedView = view

            isCheckedCategory = true

            listCheckView.add(it)

            listCheckView.forEach { view ->
                if (view == it) {
                    view.setBackgroundResource(R.drawable.select_item)
                } else {
                    view.setBackgroundResource(0)
                }
            }

            viewModel.getAllNotes()
        }
    }

    private fun initObservers() {
        viewModel.allNotes.observe(viewLifecycleOwner) {
            when(it){
                is State.Success -> {
                    viewBinding.emptyListItem.root.visibility = View.GONE
                    adapterListNotes.setData(it.listNotes)
                }
                is State.Error -> {
                    viewBinding.emptyListItem.EmptyListTextView.text = it.someError
                    viewBinding.emptyListItem.root.visibility = View.VISIBLE
                }
            }
        }

        viewModel.allNotesByGroup.observe(viewLifecycleOwner) {
            adapterListNotes.setData(it.toList())
        }

        viewModel.allNameOfGroups.observe(viewLifecycleOwner) {
            adapterListGroups.setData(it.toList())
        }

        viewModel.noteEntity.observe(viewLifecycleOwner) {
            noteUI = it
        }
    }

    private fun setupListGroupAdapter() {

        viewModel.getAllNameOfGroups()

        adapterListGroups =
            NavigationViewDrawer(object : NavigationViewDrawer.OnClickListenerDrawer {
                override fun onClick(nameGroup: String, linearLayout: LinearLayout) {

                    checkedView = view
                    isCheckedCategory = true
                    listCheckView.add(linearLayout)
                    listCheckView.forEach { view ->
                        if (view == linearLayout) {
                            view.setBackgroundResource(R.drawable.select_item)
                        } else {
                            view.setBackgroundResource(0)
                        }
                    }

                    viewModel.getAllNewsByGroup(nameGroup)
                }
            })
    }

    private fun setupNotesAdapter() {

        viewModel.getAllNotes()

        adapterListNotes = NotesAdapter(object : NotesAdapter.Callback {
            override fun onClick(noteUI: NoteUI) {
                findNavController().navigate(
                    R.id.noteDetailsFragment, bundleOf(EXTRA_ID to noteUI.id)
                )
            }

            override fun onLongClick(noteUI: NoteUI, viewItem: View) {

                popupMenu = PopupMenu(requireContext(), viewItem)

                popupMenu!!.menuInflater.inflate(
                    R.menu.popup_menu_drawer,
                    popupMenu!!.menu
                )

                popupMenu!!.setOnMenuItemClickListener {

                    when (it.itemId) {
                        R.id.editPopupDrawer -> {
                            findNavController().navigate(R.id.action_listNotesFragment_to_editFragmentKotlin)
                        }
                        R.id.deletePopupDrawer -> {
                            viewModel.deleteNote(id = noteUI.id)
                        }
                    }

                    return@setOnMenuItemClickListener true
                }
                popupMenu!!.show()
            }
        })
    }

    private fun setupDrawerRecyclerView() {
        viewBinding.includeDrawer.DrawerRecyclerView.layoutManager =
            LinearLayoutManager(requireContext())

        viewBinding.includeDrawer.DrawerRecyclerView.adapter = adapterListGroups
    }

    private fun setupRecyclerView() {
        viewBinding.myRecyclerViewForNotes.layoutManager =
            LinearLayoutManager(requireContext())

        viewBinding.myRecyclerViewForNotes.adapter = adapterListNotes

        NotesItemTouchHelperKotlin(object : NotesItemTouchHelperKotlin.Callback {
            override fun onSwipe(position: Int) {

                val note = adapterListNotes.getNoteByPosition(position)

                val snackBar =
                    Snackbar.make(view!!, "Are you sure", BaseTransientBottomBar.LENGTH_LONG);

                snackBar.setAction("Undo") {
                    viewModel.addNote(mapToNoteEntity(note))
                   // adapterListNotes.notifyItemInserted(position)
                }

                viewModel.deleteNote(adapterListNotes.getNoteByPosition(position).id)
                //adapterListNotes.notifyItemRemoved(position)

                snackBar.show()
            }

            override fun onMove(
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) {

                popupMenu!!.dismiss()

                val mFromPosition = viewHolder.adapterPosition
                val mToPosition = target.adapterPosition

                val fromNote = adapterListNotes.getListNotes().removeAt(mFromPosition);
                adapterListNotes.getListNotes().add(mToPosition, fromNote);

                viewModel.moveNotes(
                    mapToNoteEntity(adapterListNotes.getListNotes()[mFromPosition]),
                    mapToNoteEntity(adapterListNotes.getListNotes()[mToPosition])
                )
                adapterListNotes.notifyItemMoved(mFromPosition, mToPosition);
            }
        }).also {
            ItemTouchHelper(it).attachToRecyclerView(viewBinding.myRecyclerViewForNotes)
        }
    }

    private fun initDagger() {
        (activity?.application as App)
            .appComponent
            .plusNotesComponent()
            .inject(this)
    }

    private fun setupNavigationView() {
        onCallbackSupportActionBar.onEvent(viewBinding.toolbarNotes)
        navController = findNavController()
        appBarConfiguration = AppBarConfiguration(navController.graph, viewBinding.drawerLayout)
        viewBinding.toolbarNotes.setupWithNavController(navController, appBarConfiguration)
    }

    interface CallbackSupportActionBar {
        fun onEvent(toolbar: Toolbar)
    }

    companion object {

        const val EXTRA_ID = "EXTRA_ID"
        const val IS_CHECKED_CATEGORY = "IS_CHECKED_CATEGORY"

        fun newInstance() = NotesFragment()
    }
}
