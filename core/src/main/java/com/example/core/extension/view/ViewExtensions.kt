package com.example.core.extension.view

import android.view.View
import android.view.animation.Animation

fun View.startAnimation(animation: Animation, onEnd: () -> Unit, onStart: () -> Unit) {

    animation.setAnimationListener(object : Animation.AnimationListener {

        override fun onAnimationStart(p0: Animation?) = onStart()

        override fun onAnimationRepeat(p0: Animation?) = Unit

        override fun onAnimationEnd(p0: Animation?) = onEnd()
    })
    this.startAnimation(animation)
}

fun View.setVisibility(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
}

fun View.setGone(isGone: Boolean) {
    this.visibility = if (isGone) View.GONE else View.VISIBLE
}
