package com.classy.gamesinfo.ui.main.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.classy.gamesinfo.data.model.gamespot.Article
import com.classy.gamesinfo.data.model.gamespot.ArticlesJsonAPI
import com.classy.gamesinfo.databinding.FragmentNewsBinding
import com.classy.gamesinfo.ui.main.adapter.ArticlesAdapter
import com.classy.gamesinfo.ui.main.viewmodel.ArticlesViewModel
import com.classy.gamesinfo.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.ArrayList

class ArticlesFragment : Fragment() {

    private val articlesViewModel: ArticlesViewModel by viewModel()
    private lateinit var articlesAdapter: ArticlesAdapter
    private var _binding: FragmentNewsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNewsBinding.inflate(inflater, container, false)

        setupUI()
        setupObservers()

        return binding.root
    }

    private fun setupUI() {
        binding.newsLSTRecyclerView.layoutManager = LinearLayoutManager(activity)
        articlesAdapter = ArticlesAdapter(arrayListOf())
        binding.newsLSTRecyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.newsLSTRecyclerView.context,
                (binding.newsLSTRecyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.newsLSTRecyclerView.adapter = articlesAdapter
    }

    private fun setupObservers() {
        articlesViewModel.getRecentArticles().observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.newsLSTRecyclerView.visibility = View.VISIBLE
                        binding.newsBARProgressBar.visibility = View.GONE
                        resource.data?.let { articles ->
                            retrieveList(articles)
                        }
                        Log.d("FFFF", "getRecentArticles(): SUCCESS")
                    }
                    Status.ERROR -> {
                        binding.newsLSTRecyclerView.visibility = View.VISIBLE
                        binding.newsBARProgressBar.visibility = View.GONE
                        Log.d("FFFF", "getRecentArticles(): " + it.message.toString())
                    }
                    Status.LOADING -> {
                        binding.newsBARProgressBar.visibility = View.VISIBLE
                        binding.newsLSTRecyclerView.visibility = View.GONE
                        Log.d("FFFF", "getRecentArticles(): LOADING")
                    }
                }
            }
        })
    }

    private fun retrieveList(articles: ArticlesJsonAPI) {
        articlesAdapter.apply{
            //addArticles(articles.results)
            notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}