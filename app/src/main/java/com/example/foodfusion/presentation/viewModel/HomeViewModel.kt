package com.example.foodfusion.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodfusion.data.model.CategoryItem
import com.example.foodfusion.data.repository.MealRepository
import com.example.foodfusion.utils.Resource
import kotlinx.coroutines.launch

class HomeViewModel(private val mealRepository: MealRepository) : ViewModel() {

    private val _categories = MutableLiveData<Resource<List<CategoryItem>>>()
    val categories: LiveData<Resource<List<CategoryItem>>> get() = _categories

    fun getCategories() {
        viewModelScope.launch {
            _categories.value = Resource.Loading
            _categories.value = mealRepository.getCategories()
        }
    }
}

