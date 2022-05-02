package com.example.notes.features.edit.di

import com.example.notes.features.edit.ui.EditFragment
import dagger.Subcomponent

@Subcomponent(modules = [EditModule::class])
interface EditComponent {
    fun inject(editFragment: EditFragment)
}
