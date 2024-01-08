package com.example.foodfusion.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodfusion.data.model.MealItem
import com.example.foodfusion.databinding.ItemRecommendationBinding

class RecommendationAdapter(private val dataList: List<MealItem>) :
    RecyclerView.Adapter<RecommendationAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemRecommendationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(mealItem: MealItem) {
            // Set data to views
            Glide.with(binding.ivMeal.context)
                .load(mealItem.imageResId)
                .into(binding.ivMeal)

            binding.tvMeal.text = mealItem.name
            binding.tvArea.text = mealItem.area
            binding.tvCategory.text = mealItem.category
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecommendationBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}