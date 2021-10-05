package com.example.notes.presentation.listNotes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.App
import com.example.notes.R
import com.example.notes.data.locale.entity.NoteEntityKotlin
import com.example.notes.databinding.FragmentNotesBinding
import com.example.notes.presentation.listGroup.recycler.NavigationViewDrawerKotlin
import com.example.notes.presentation.listNotes.recycler.NotesAdapterKotlin
import com.example.notes.presentation.listNotes.touchHelper.NotesItemTouchHelperKotlin
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class ListNotesFragmentKotlin : Fragment(R.layout.fragment_notes) {

    private var isCheckedCategory = false
    private var checkedView:View? = null

    private var listCheckView = mutableListOf<View>()

    lateinit var viewBinding: FragmentNotesBinding

    lateinit var adapterListNotesKotlin: NotesAdapterKotlin

    lateinit var adapterListGroupsKotlin: NavigationViewDrawerKotlin

    private var popupMenu: PopupMenu? = null

    private lateinit var onCallbackSupportActionBar: CallbackSupportActionBar

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var navController: NavController

    private var noteEntityKotlin: NoteEntityKotlin? = null

    @Inject
    lateinit var viewModelKotlin: NotesViewModelKotlin

    override fun onAttach(context: Context) {
        super.onAttach(context)

        onCallbackSupportActionBar = context as CallbackSupportActionBar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retainInstance = true;
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

        adapterListNotesKotlin = NotesAdapterKotlin(object : NotesAdapterKotlin.Callback {
            override fun onClick(noteEntityKotlin: NoteEntityKotlin) {
                findNavController().navigate(
                    R.id.notesFragmentWrite, bundleOf(EXTRA_ID to noteEntityKotlin.id)
                )
            }

            override fun onLongClick(noteEntityKotlin: NoteEntityKotlin, viewItem: View) {

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
                            viewModelKotlin.deleteNote(noteEntityKotlin = noteEntityKotlin)
                        }
                    }

                    return@setOnMenuItemClickListener true
                }
                popupMenu!!.show()
            }
        })

        adapterListGroupsKotlin =
            NavigationViewDrawerKotlin(object : NavigationViewDrawerKotlin.OnClickListenerDrawer {
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

                    viewModelKotlin.getAllNewsByGroup(nameGroup)
                }
            })

        setupRecyclerView()

        setupDrawerRecyclerView()

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

            viewModelKotlin.getAllNews()
        }

        viewModelKotlin.allNotes.observe(viewLifecycleOwner) {
            adapterListNotesKotlin.setData(it.toList())
        }

        viewModelKotlin.allNotesByGroup.observe(viewLifecycleOwner) {
            adapterListNotesKotlin.setData(it.toList())
        }

        viewModelKotlin.allNameOfGroups.observe(viewLifecycleOwner) {
            adapterListGroupsKotlin.setData(it.toList())
        }

        viewModelKotlin.noteEntityKotlin.observe(viewLifecycleOwner) {
            noteEntityKotlin = it
        }

        viewModelKotlin.allNotesByGroup.observe(viewLifecycleOwner) {
            adapterListNotesKotlin.setData(it.toList())
        }

        viewBinding.floatingButtonNotes.setOnClickListener {
            findNavController().navigate(R.id.notesFragmentWrite)
        }

        if (isCheckedCategory) {
            checkedView?.setBackgroundResource(R.drawable.select_item)
        }
    }

    private fun setupDrawerRecyclerView() {
        viewBinding.includeDrawer.DrawerRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewBinding.includeDrawer.DrawerRecyclerView.adapter = adapterListGroupsKotlin
    }

    private fun setupRecyclerView() {
        viewBinding.myRecyclerViewForNotes.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewBinding.myRecyclerViewForNotes.adapter = adapterListNotesKotlin

        NotesItemTouchHelperKotlin(object : NotesItemTouchHelperKotlin.Callback {
            override fun onSwipe(position: Int) {

                val note = adapterListNotesKotlin.getNoteByPosition(position)

                val snackBar =
                    Snackbar.make(view!!, "Are you sure", BaseTransientBottomBar.LENGTH_LONG);
                snackBar.setAction("Undo") {
                    viewModelKotlin.addNote(note)
                }

                viewModelKotlin.deleteNote(adapterListNotesKotlin.getNoteByPosition(position))
                snackBar.show()
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) {

                popupMenu!!.dismiss()

                val mFromPosition = viewHolder.adapterPosition
                val mToPosition = target.adapterPosition

                val fromNote = adapterListNotesKotlin.getListNotes().removeAt(mFromPosition);
                adapterListNotesKotlin.getListNotes().add(mToPosition, fromNote);

                viewModelKotlin.moveNotes(
                    adapterListNotesKotlin.getListNotes()[mFromPosition],
                    adapterListNotesKotlin.getListNotes()[mToPosition
                    ]
                )
                adapterListNotesKotlin.notifyItemMoved(mFromPosition, mToPosition);
            }
        }).also { ItemTouchHelper(it).attachToRecyclerView(viewBinding.myRecyclerViewForNotes) }
    }

    private fun initDagger() {
        (activity?.application as App).appComponent.noteComponent().create().inject(this)
    }

    private fun setupNavigationView() {
        onCallbackSupportActionBar.onEvent(viewBinding.toolbarNotes)
        navController = findNavController()
        appBarConfiguration = AppBarConfiguration(navController.graph, viewBinding.myDrawerLayout)
        viewBinding.toolbarNotes.setupWithNavController(navController, appBarConfiguration)
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putBoolean(IS_CHECKED_CATEGORY, isCheckedCategory)
//    }
//
//    override fun onViewStateRestored(savedInstanceState: Bundle?) {
//        super.onViewStateRestored(savedInstanceState)
//        Toast.makeText(requireContext(), "${savedInstanceState?.getBoolean(IS_CHECKED_CATEGORY, false)}", Toast.LENGTH_SHORT).show()
//
//        if (savedInstanceState != null) {
//            isCheckedCategory = savedInstanceState.getBoolean(IS_CHECKED_CATEGORY, false)
//            checkedView?.setBackgroundResource(R.drawable.select_item)
//        }
//    }

    interface CallbackSupportActionBar {
        fun onEvent(toolbar: Toolbar)
    }

    companion object {

        const val EXTRA_ID = "EXTRA_ID"
        const val IS_CHECKED_CATEGORY = "IS_CHECKED_CATEGORY"

        fun newInstance() = ListNotesFragmentKotlin()
    }
}