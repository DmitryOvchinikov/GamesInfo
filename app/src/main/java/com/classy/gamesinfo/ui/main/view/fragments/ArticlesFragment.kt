package com.classy.gamesinfo.ui.main.view.fragments

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.classy.gamesinfo.data.model.gamespot.ArticleJsonAPI
import com.classy.gamesinfo.data.model.gamespot.ResultArticle
import com.classy.gamesinfo.databinding.FragmentArticlesBinding
import com.classy.gamesinfo.ui.main.adapter.ArticlesAdapter
import com.classy.gamesinfo.ui.main.listeners.EndlessRecyclerOnScrollListener
import com.classy.gamesinfo.ui.main.viewmodel.ArticlesViewModel
import com.classy.gamesinfo.utils.Status
import org.koin.androidx.viewmodel.ext.android.stateViewModel


class ArticlesFragment : Fragment() {

    private val articlesViewModel: ArticlesViewModel by stateViewModel()
    private lateinit var articlesAdapter: ArticlesAdapter
    private var _binding: FragmentArticlesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticlesBinding.inflate(inflater, container, false)

        setupUI()
        setupObservers()

        return binding.root

    }

    private fun setupUI() {
        binding.articlesLSTRecyclerView.layoutManager = LinearLayoutManager(activity)
        articlesAdapter = ArticlesAdapter(arrayListOf())
        binding.articlesLSTRecyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.articlesLSTRecyclerView.context,
                (binding.articlesLSTRecyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.articlesLSTRecyclerView.adapter = articlesAdapter
        binding.articlesLSTRecyclerView.addOnScrollListener(object :
            EndlessRecyclerOnScrollListener() {
            override fun onLoadMore() {
                loadMoreArticles()
            }
        })
    }

    private fun loadMoreArticles() {
        articlesViewModel.getRecentArticles(
            "json",
            "publish_date:desc",
            10,
            articlesAdapter.itemCount
        ).observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { articles ->
                            retrieveList(articles)
                        }
                        Log.d("FFFF", "loadMoreArticles(): getRecentArticles(): SUCCESS")
                    }
                    Status.ERROR -> {
                        Log.d(
                            "FFFF",
                            "loadMoreArticles(): getRecentArticles(): " + it.message.toString()
                        )
                        if (it.message.toString() == "HTTP 502") {
                            //TODO: Check if this doesn't crash the app
                            loadMoreArticles()
                        }
                    }
                    Status.LOADING -> {
                        Log.d("FFFF", "loadMoreArticles(): getRecentArticles(): LOADING")
                    }
                }
            }
        })
    }

    private fun setupObservers() {
        articlesViewModel.getRecentArticles("json", "publish_date:desc", 10, 0)
            .observe(viewLifecycleOwner, {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            binding.articlesLSTRecyclerView.visibility = View.VISIBLE
                            binding.articlesBARProgressBar.visibility = View.GONE
                            resource.data?.let { articles ->
                                retrieveList(articles)
                            }
                            Log.d("FFFF", "getRecentArticles(): SUCCESS")
                        }
                        Status.ERROR -> {
                            binding.articlesLSTRecyclerView.visibility = View.VISIBLE
                            binding.articlesBARProgressBar.visibility = View.GONE
                            Log.d("FFFF", "getRecentArticles(): " + it.message.toString())
                        }
                        Status.LOADING -> {
                            binding.articlesBARProgressBar.visibility = View.VISIBLE
                            binding.articlesLSTRecyclerView.visibility = View.GONE
                            Log.d("FFFF", "getRecentArticles(): LOADING")
                        }
                    }
                }
            })
    }

    private fun retrieveList(articleAPI: ArticleJsonAPI) {
        articlesAdapter.apply {
            addArticles(articleAPI.results as ArrayList<ResultArticle>)
            notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("FFFF", "onDestroyView()")
        _binding = null
    }

    override fun onPause() {
        super.onPause()
        Log.d("FFFF", "onPause()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("FFFF", "onResume()")
    }
}