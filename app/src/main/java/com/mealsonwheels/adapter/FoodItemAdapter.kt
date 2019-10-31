package com.mealsonwheels.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mealsonwheels.R
import com.mealsonwheels.model.FoodItem
import com.mealsonwheels.ui.FoodItem.FoodItemFragment
import kotlinx.android.synthetic.main.row_food_item.view.*

class FoodItemAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<FoodItem> = ArrayList()
    private lateinit var listener: ItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_food_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is FoodItemAdapter.ItemViewHolder -> {
                holder.bind(items[position])


                holder.itemCard.setOnClickListener {
                    this.listener.onItemClicked(it, items[position])
                }


                holder.btnQuantityAdd.setOnClickListener {
                    this.listener.onAddItem(it, items[position])
                }
                holder.btnQuantitySub.setOnClickListener {
                    this.listener.onRemoveItem(it, items[position])
                }



            }
        }

    }
    override fun getItemCount(): Int {
      return items.size
    }

    fun submitList(fooditemList: ArrayList<FoodItem>) {
        items = fooditemList
    }

    fun setItemOnClickListener(listener: FoodItemFragment){
        this.listener = listener
    }

    class ItemViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.item_image
        val itemName: TextView = itemView.item_name
        val itemPrice:TextView = itemView.item_price
        val itemCard : CardView = itemView.food_item_card
        val btnQuantityAdd: ImageButton = itemView.btn_quantity_add
        val btnQuantitySub: ImageButton = itemView.btn_quantity_sub
        val quantityLayout: LinearLayout = itemView.layout_quantity

        fun bind(foodItem: FoodItem) {
            itemName.text = foodItem.name
            itemPrice.text = foodItem.price

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(foodItem.image)
                .into(itemImage)
        }

    }

    interface ItemClickListener{
        fun onItemClicked(view: View, item:FoodItem)
        fun onAddItem(view: View, item: FoodItem)
        fun onRemoveItem(view: View, item: FoodItem)
    }
}