package com.example.foodfusion.presentation.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodfusion.R
import com.example.foodfusion.data.model.CategoryResponse
import com.example.foodfusion.data.model.MealItem
import com.example.foodfusion.data.remote.ApiClient
import com.example.foodfusion.data.repository.MealRepository
import com.example.foodfusion.databinding.FragmentHomeBinding
import com.example.foodfusion.presentation.adapter.CategoryAdapter
import com.example.foodfusion.presentation.adapter.RecommendationAdapter
import com.example.foodfusion.presentation.viewModel.HomeViewModel
import com.example.foodfusion.presentation.viewModel.ViewModelFactory
import com.example.foodfusion.utils.Resource
import com.facebook.shimmer.ShimmerFrameLayout

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var recommendationAdapter: RecommendationAdapter
    private lateinit var viewModel: HomeViewModel
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProvider(this, ViewModelFactory(MealRepository(ApiClient.apiService)))
            .get(HomeViewModel::class.java)


        observeCategories()

        viewModel.getCategories()

        // Dummy data for Recommendation
        val recommendationItemList = listOf(
            MealItem("Fried Chicken", R.drawable.ic_friend_chicken, "American", "Chicken"),
        )
        recommendationAdapter = RecommendationAdapter(recommendationItemList)
        binding.rvRecommendation.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvRecommendation.adapter = recommendationAdapter

        // Setup RecyclerView for Categories with GridLayoutManager
        categoryAdapter = CategoryAdapter(mutableListOf())
        binding.rvCategories.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCategories.adapter = categoryAdapter


        val shimmerRecommendation: ShimmerFrameLayout = binding.shimmerRecommendation
        val shimmerCategories: ShimmerFrameLayout = binding.shimmerCategories

        // Data loading completed, stop shimmer animations after 1 second delay
        Handler().postDelayed({
            shimmerRecommendation.stopShimmer()
            shimmerRecommendation.visibility = View.GONE

            shimmerCategories.stopShimmer()
            shimmerCategories.visibility = View.GONE
        }, 1000)
    }

    private fun observeCategories() {
        viewModel.categories.observe(viewLifecycleOwner) { resource ->
            if (resource is Resource.Success) {
                categoryAdapter.setData(resource.data)
            } else if (resource is Resource.Error) {
                Toast.makeText(
                    requireContext(),
                    resource.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


}
