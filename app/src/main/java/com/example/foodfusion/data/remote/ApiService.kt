package com.example.foodfusion.data.remote

import com.example.foodfusion.data.model.CategoryResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("categories.php")
    suspend fun getCategories(): Response<CategoryResponse>

    // Tambahkan metode lain sesuai kebutuhan (mendapatkan detail resep, dsb.)
}