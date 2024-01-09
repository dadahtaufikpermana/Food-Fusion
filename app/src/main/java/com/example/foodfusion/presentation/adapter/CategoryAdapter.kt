package com.example.foodfusion.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodfusion.data.model.CategoryItem
import com.example.foodfusion.data.model.CategoryResponse
import com.example.foodfusion.databinding.ItemCategoryBinding
import com.example.foodfusion.utils.CategoryDiffCallback

class CategoryAdapter(private val dataList: MutableList<CategoryItem>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(categoryItem: CategoryItem) {
            Glide.with(binding.ivCategory.context)
                .load(categoryItem.image)
                .into(binding.ivCategory)

            binding.tvCategory.text = categoryItem.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(newData: List<CategoryItem>) {
        val diffResult = DiffUtil.calculateDiff(CategoryDiffCallback(dataList, newData))

        dataList.clear()
        dataList.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }

}