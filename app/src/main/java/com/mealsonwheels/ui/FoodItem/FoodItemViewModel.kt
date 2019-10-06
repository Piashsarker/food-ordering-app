package com.mealsonwheels.ui.FoodItem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FoodItemViewModel: ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Food Item Fragment"
    }
    val text: LiveData<String> = _text
}