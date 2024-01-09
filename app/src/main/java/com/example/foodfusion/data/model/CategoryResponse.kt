package com.example.foodfusion.data.model

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("categories") val categories: List<CategoryItem>
)

data class CategoryItem(
    @SerializedName("idCategory") val id: String,
    @SerializedName("strCategory") val name: String,
    @SerializedName("strCategoryThumb") val image: String,
    @SerializedName("strCategoryDescription") val description: String
)
