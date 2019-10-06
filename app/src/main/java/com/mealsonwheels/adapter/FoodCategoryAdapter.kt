package com.mealsonwheels.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mealsonwheels.R
import com.mealsonwheels.model.FoodCategory
import kotlinx.android.synthetic.main.row_food_category.view.*

class FoodCategoryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<FoodCategory> = ArrayList()
    private lateinit var listener: CategoryClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BlogViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_food_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BlogViewHolder -> {
                holder.bind(items.get(position))
                /**
                 * Category Card Listener.
                 */
                holder.categoryCard.setOnClickListener {
                    this.listener.onCategoryClicked(it, items[position])
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(categoryList: List<FoodCategory>) {
        items = categoryList
    }

    fun setCategoryOnClickListener(listener: CategoryClickListener){
        this.listener = listener
    }

    class BlogViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryImage: ImageView = itemView.category_image
        val categoryTitle: TextView = itemView.category_name
        val categoryCard : CardView = itemView.category_card

        fun bind(category: FoodCategory) {
            categoryTitle.setText(category.name)

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(category.image)
                .into(categoryImage)
        }

    }

    interface CategoryClickListener{
        fun  onCategoryClicked(view: View, category: FoodCategory)
    }

}


