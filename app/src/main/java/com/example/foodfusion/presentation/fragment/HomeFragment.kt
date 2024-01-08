package com.example.foodfusion.presentation.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodfusion.R
import com.example.foodfusion.data.model.CategoryItem
import com.example.foodfusion.data.model.MealItem
import com.example.foodfusion.databinding.FragmentHomeBinding
import com.example.foodfusion.presentation.adapter.CategoryAdapter
import com.example.foodfusion.presentation.adapter.RecommendationAdapter
import com.facebook.shimmer.ShimmerFrameLayout

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var recommendationAdapter: RecommendationAdapter
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Dummy data for Recommendation
        val recommendationItemList = listOf(
            MealItem("Fried Chicken", R.drawable.ic_friend_chicken, "American", "Chicken"),
        )
        recommendationAdapter = RecommendationAdapter(recommendationItemList)
        binding.rvRecommendation.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvRecommendation.adapter = recommendationAdapter

        // Dummy data for Categories
        val categoryItemList = listOf(
            CategoryItem("Beef", R.drawable.ic_friend_chicken),
            CategoryItem("Beef", R.drawable.ic_friend_chicken),
            CategoryItem("Beef", R.drawable.ic_friend_chicken),
            CategoryItem("Beef", R.drawable.ic_friend_chicken),
        )

        // Setup RecyclerView for Categories with GridLayoutManager
        categoryAdapter = CategoryAdapter(categoryItemList)
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
}
