package com.example.foodfusion.data.repository


import com.example.foodfusion.data.model.CategoryItem
import com.example.foodfusion.data.remote.ApiService
import com.example.foodfusion.utils.Resource

class MealRepository(private val apiService: ApiService) {

    suspend fun getCategories(): Resource<List<CategoryItem>> {
        return try {
            val response = apiService.getCategories()
            if (response.isSuccessful) {
                Resource.Success(response.body()?.categories ?: emptyList())
            } else {
                Resource.Error("Failed to get categories")
            }
        } catch (e: Exception) {
            Resource.Error("An error occurred: ${e.message}")
        }
    }
}
