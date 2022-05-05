package com.example.notes.features.edit.ui

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.core.view.children
import com.example.notes.R
import com.example.notes.databinding.FragmentEditBinding
import com.example.notes.models.NoteUi

class SimpleColorPicker(
    private val binding: FragmentEditBinding,
    private val noteUi: NoteUi
) : ColorPicker {

    private var selectedColor = 0

    private val colors = intArrayOf(
        R.color.red, R.color.pink, R.color.purple,
        R.color.deep_purple, R.color.blue,
        R.color.cyan, R.color.teal, R.color.green,
        R.color.light_green, R.color.lime, R.color.yellow,
        R.color.amber, R.color.orange, R.color.deep_orange,
        R.color.red, R.color.brown, R.color.grey,
        R.color.blue_grey
    )

    init {
        setupContainerOfColors()
    }

    private fun setupContainerOfColors() {
        repeat(colors.count()) { index ->
            val placeHolderColor =
                LayoutInflater.from(binding.root.context).inflate(R.layout.place_holder_color, null)
            val itemColor = placeHolderColor.findViewById<ImageView>(R.id.itemOvalColor)
            val itemCheckColor = placeHolderColor.findViewById<ImageView>(R.id.check)
            itemColor.setOnClickListener {
                when (itemCheckColor.visibility) {
                    View.VISIBLE -> itemCheckColor.visibility = View.INVISIBLE
                    View.INVISIBLE -> {
                        binding.containerColorsEditScreen.children.forEach {
                            it.findViewById<ImageView>(R.id.check).visibility = View.INVISIBLE
                        }
                        itemCheckColor.visibility = View.VISIBLE
                        selectedColor = colors[index]
                    }
                    View.GONE -> throw IllegalStateException()
                }
            }
            itemColor.setColorFilter(binding.root.resources.getColor(colors[index], null))
            checkCurrentNoteUiColor(index, itemCheckColor)
            binding.containerColorsEditScreen.addView(placeHolderColor)
        }
    }

    private fun checkCurrentNoteUiColor(index: Int, itemCheckColor: ImageView) {
        if (colors[index] == noteUi.colorBackground){
            itemCheckColor.visibility = View.VISIBLE
            selectedColor = noteUi.colorBackground
        }
    }

    override fun getColor(): Int {
        return selectedColor
    }
}
