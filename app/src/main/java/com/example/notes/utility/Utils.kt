package com.example.notes.utility

import androidx.navigation.NavController

fun NavController.navigateToAndRemoveFromBackStack(directionId: Int) {
    this.popBackStack()
    navigate(directionId)
}