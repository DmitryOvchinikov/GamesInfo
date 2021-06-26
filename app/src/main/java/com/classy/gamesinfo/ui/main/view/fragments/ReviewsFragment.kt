package com.classy.gamesinfo.ui.main.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.classy.gamesinfo.data.model.gamespot.ResultReview
import com.classy.gamesinfo.data.model.gamespot.ReviewJsonAPI
import com.classy.gamesinfo.databinding.FragmentReviewsBinding
import com.classy.gamesinfo.ui.main.adapter.ReviewsAdapter
import com.classy.gamesinfo.ui.main.listeners.EndlessRecyclerOnScrollListener
import com.classy.gamesinfo.ui.main.viewmodel.ReviewsViewModel
import com.classy.gamesinfo.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReviewsFragment : Fragment() {

    private val reviewsViewModel: ReviewsViewModel by viewModel()
    private lateinit var reviewsAdapter: ReviewsAdapter
    private var _binding: FragmentReviewsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReviewsBinding.inflate(inflater, container, false)

        setupUI()
        setupObservers()

        return binding.root
    }

    private fun setupUI() {
        binding.reviewsLSTRecyclerView.layoutManager = LinearLayoutManager(activity)
        reviewsAdapter = ReviewsAdapter(arrayListOf())
        binding.reviewsLSTRecyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.reviewsLSTRecyclerView.context,
                (binding.reviewsLSTRecyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.reviewsLSTRecyclerView.adapter = reviewsAdapter
        binding.reviewsLSTRecyclerView.addOnScrollListener(object :
            EndlessRecyclerOnScrollListener() {
            override fun onLoadMore() {
                loadMoreReviews()
            }
        })
    }

    private fun loadMoreReviews() {
        reviewsViewModel.getRecentReviews(
            "json",
            "publish_date:desc",
            10,
            reviewsAdapter.itemCount
        ).observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { reviews ->
                            retrieveList(reviews)
                        }
                        Log.d("FFFF", "loadMoreReviews(): getRecentReviews(): SUCCESS")
                    }
                    Status.ERROR -> {
                        Log.d(
                            "FFFF",
                            "loadMoreReviews(): getRecentReviews(): " + it.message.toString()
                        )
                        if (it.message.toString() == "HTTP 502") {
                            //TODO: Check if this doesn't crash the app
                            loadMoreReviews()
                        }
                    }
                    Status.LOADING -> {
                        Log.d("FFFF", "loadMoreReviews(): getRecentReviews(): LOADING")
                    }
                }
            }
        })
    }

    private fun setupObservers() {
        reviewsViewModel.getRecentReviews("json", "publish_date:desc", 10, 0)
            .observe(viewLifecycleOwner, {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            binding.reviewsLSTRecyclerView.visibility = View.VISIBLE
                            binding.reviewsBARProgressBar.visibility = View.GONE
                            resource.data?.let { reviews ->
                                retrieveList(reviews)
                            }
                            Log.d("FFFF", "getRecentReviews(): SUCCESS")
                        }
                        Status.ERROR -> {
                            binding.reviewsLSTRecyclerView.visibility = View.VISIBLE
                            binding.reviewsBARProgressBar.visibility = View.GONE
                            Log.d("FFFF", "getRecentReviews(): " + it.message.toString())
                        }
                        Status.LOADING -> {
                            binding.reviewsBARProgressBar.visibility = View.VISIBLE
                            binding.reviewsLSTRecyclerView.visibility = View.GONE
                            Log.d("FFFF", "getRecentReviews(): LOADING")
                        }
                    }
                }
            })
    }

    private fun retrieveList(api: ReviewJsonAPI) {
        reviewsAdapter.apply {
            addReviews(api.results as ArrayList<ResultReview>)
            notifyDataSetChanged()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("FFFF", "onDestroyView()")
        _binding = null
    }
}