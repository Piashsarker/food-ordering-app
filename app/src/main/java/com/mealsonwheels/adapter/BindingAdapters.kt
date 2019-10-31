package com.mealsonwheels.adapter

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("hideView")
fun hideView(view: View, hideView: Boolean) {
    if (hideView) {
        view.visibility = View.GONE
    }
    view.visibility = View.VISIBLE
}