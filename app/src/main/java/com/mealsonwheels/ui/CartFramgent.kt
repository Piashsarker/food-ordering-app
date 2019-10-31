package com.mealsonwheels.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mealsonwheels.R
import com.mealsonwheels.adapter.FoodItemAdapter
import com.mealsonwheels.data.DataSource
import com.mealsonwheels.databinding.FragmentCartBinding

/**
 * A simple [Fragment] subclass.
 */
class CartFramgent : Fragment() {

    lateinit var binding: FragmentCartBinding
    private lateinit var foodItemAdpter: FoodItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        addDataSet()
    }


    private fun initRecyclerView() {
        binding.foodItemRecylerview.apply {
            layoutManager = LinearLayoutManager(activity)
            foodItemAdpter = FoodItemAdapter()
            adapter = foodItemAdpter
        }
    }

    private fun addDataSet() {
        // please change datasource
        val data = DataSource.cartItems

        if (data.isEmpty()) {
            binding.txtCartEmpty.visibility = View.VISIBLE
            binding.layoutCart.visibility = View.GONE
        } else {
            binding.layoutCart.visibility = View.VISIBLE
            binding.txtCartEmpty.visibility = View.GONE
        }



        foodItemAdpter.submitList(data)
    }

}
