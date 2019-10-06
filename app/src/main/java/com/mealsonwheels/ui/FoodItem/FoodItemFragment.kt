package com.mealsonwheels.ui.FoodItem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mealsonwheels.R
import com.mealsonwheels.adapter.FoodItemAdapter
import com.mealsonwheels.data.DataSource
import com.mealsonwheels.databinding.FragmentFoodItemBinding
import com.mealsonwheels.model.FoodItem
import kotlinx.android.synthetic.main.fragment_food_item.*

class FoodItemFragment:Fragment(), FoodItemAdapter.ItemClickListener {

    private lateinit var fooditemViewModel:FoodItemViewModel
    private  lateinit var foodItemAdpter: FoodItemAdapter

    private lateinit var binding: FragmentFoodItemBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fooditemViewModel =
            ViewModelProviders.of(this).get(FoodItemViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_food_item,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        addDataSet()

    }

    private fun  addDataSet()
    {
        // please change datasource
        val data = DataSource.getFoodItem()
        foodItemAdpter.submitList(data)
        foodItemAdpter.setItemOnClickListener(this)
    }

    override fun onItemClicked(view: View, item: FoodItem) {
        Toast.makeText(activity,"Item Clikced "+item.name, Toast.LENGTH_LONG).show()
    }

    private  fun initRecyclerView()
    {
        food_item_recylerview.apply {
            layoutManager = LinearLayoutManager(activity)
            foodItemAdpter = FoodItemAdapter()
            adapter = foodItemAdpter
        }
    }
}