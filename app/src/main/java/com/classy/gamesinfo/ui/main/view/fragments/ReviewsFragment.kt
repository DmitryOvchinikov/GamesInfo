package com.classy.gamesinfo.ui.main.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.classy.gamesinfo.databinding.FragmentReviewsBinding
import com.classy.gamesinfo.ui.main.viewmodel.ReviewsViewModel

class ReviewsFragment : Fragment() {

    private lateinit var reviewsViewModel: ReviewsViewModel
    private var _binding: FragmentReviewsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        reviewsViewModel =
            ViewModelProvider(this).get(ReviewsViewModel::class.java)

        _binding = FragmentReviewsBinding.inflate(inflater, container, false)

//        val textView: TextView = binding.textDashboard
//        reviewsViewModel.text.observe(viewLifecycleOwner, {
//            textView.text = it
//        })
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}