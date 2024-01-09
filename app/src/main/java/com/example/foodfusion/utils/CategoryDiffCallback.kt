package com.example.foodfusion.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.foodfusion.data.model.CategoryItem

class CategoryDiffCallback(
    private val oldList: List<CategoryItem>,
    private val newList: List<CategoryItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
