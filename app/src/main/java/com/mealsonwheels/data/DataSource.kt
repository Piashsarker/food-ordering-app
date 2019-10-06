package com.mealsonwheels.data

import com.mealsonwheels.R
import com.mealsonwheels.model.FoodCategory

class DataSource {

    companion object {

        fun getFoodCategories(): ArrayList<FoodCategory> {
            val list = ArrayList<FoodCategory>()
            list.add(FoodCategory("1", "Fast Food", R.drawable.fastfood))
            list.add(FoodCategory("2", "Set  Menu", R.drawable.set_menu))
            list.add(FoodCategory("1", "Drinks", R.drawable.drinks))
            list.add(FoodCategory("1", "Platter For Couple", R.drawable.platter_for_couple))
            return list
        }

    }
}