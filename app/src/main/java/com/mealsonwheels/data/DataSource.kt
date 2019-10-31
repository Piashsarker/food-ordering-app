package com.mealsonwheels.data

import com.mealsonwheels.R
import com.mealsonwheels.model.FoodCategory
import com.mealsonwheels.model.FoodItem

class DataSource {

    companion object {

        val cartItems: ArrayList<FoodItem> = ArrayList()

        fun getFoodCategories(): ArrayList<FoodCategory> {
            val list = ArrayList<FoodCategory>()
            list.add(FoodCategory("1", "Fast Food", R.drawable.fastfood))
            list.add(FoodCategory("2", "Set  Menu", R.drawable.set_menu))
            list.add(FoodCategory("1", "Drinks", R.drawable.drinks))
            list.add(FoodCategory("1", "Platter For Couple", R.drawable.platter_for_couple))
            return list
        }

        fun getFoodItem(): ArrayList<FoodItem> {
            val list = ArrayList<FoodItem>()
            list.add(FoodItem("1", "Mini Burger", R.drawable.fastfood,"10.00 BDT",1,""))
            list.add(FoodItem("2", "Chicken Fry", R.drawable.drinks,"1000.12 BDT",1,""))
            list.add(FoodItem("1", "Sub Sandwich", R.drawable.fastfood,"10.00 BDT",1,""))
            list.add(FoodItem("2", "Fried Rice", R.drawable.drinks,"1000.12 BDT",1,""))
            list.add(FoodItem("1", "Prawn Fry", R.drawable.fastfood,"10.00 BDT",1,""))
            list.add(FoodItem("2", "Prawn Grill", R.drawable.drinks,"1000.12 BDT",1,""))

            return list
        }

        fun addToCart(foodItem: FoodItem) {
            cartItems.add(foodItem)
        }

        fun removeFromCart(foodItem: FoodItem) {
            cartItems.remove(foodItem)
        }





    }
}