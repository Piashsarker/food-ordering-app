package com.mealsonwheels.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mealsonwheels.R
import com.mealsonwheels.adapter.FoodCategoryAdapter
import com.mealsonwheels.data.DataSource
import com.mealsonwheels.model.FoodCategory
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), FoodCategoryAdapter.CategoryClickListener {



    private lateinit var homeViewModel: HomeViewModel
    private  lateinit var categoryAdapter: FoodCategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        addDataSet()
    }

    private fun  addDataSet()
    {
        val data = DataSource.getFoodCategories()
        categoryAdapter.submitList(data)
        categoryAdapter.setCategoryOnClickListener(this)
    }


    override fun onCategoryClicked(view: View, category: FoodCategory) {
        Toast.makeText(activity,"Item Clikced "+category.name,Toast.LENGTH_LONG).show()
    }

    private  fun initRecyclerView()
    {
        food_category_recylerview.apply {
            layoutManager = LinearLayoutManager(activity)
            categoryAdapter = FoodCategoryAdapter()
            adapter = categoryAdapter
        }
    }
}