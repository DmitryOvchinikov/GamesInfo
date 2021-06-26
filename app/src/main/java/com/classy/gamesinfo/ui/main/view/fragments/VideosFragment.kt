package com.classy.gamesinfo.ui.main.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import cn.jzvd.Jzvd
import com.classy.gamesinfo.data.model.gamespot.ResultVideo
import com.classy.gamesinfo.data.model.gamespot.VideoJsonAPI
import com.classy.gamesinfo.databinding.FragmentVideosBinding
import com.classy.gamesinfo.ui.main.adapter.VideosAdapter
import com.classy.gamesinfo.ui.main.listeners.EndlessRecyclerOnScrollListener
import com.classy.gamesinfo.ui.main.viewmodel.VideosViewModel
import com.classy.gamesinfo.utils.Status
import org.jzvd.jzvideo.JZVideoA
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideosFragment : Fragment() {

    private val videosViewModel: VideosViewModel by viewModel()
    private var _binding: FragmentVideosBinding? = null
    private lateinit var videosAdapter: VideosAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentVideosBinding.inflate(inflater, container, false)

        setupUI()
        setupObservers()

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Jzvd.releaseAllVideos()
                    Jzvd.backPress()
                    if (isEnabled) {
                        isEnabled = false
                        requireActivity().onBackPressed()
                    }
                }

            })

        return binding.root
    }

    private fun setupUI() {
        binding.videosLSTRecyclerView.layoutManager = LinearLayoutManager(activity)
        videosAdapter = VideosAdapter(arrayListOf())
        binding.videosLSTRecyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.videosLSTRecyclerView.context,
                (binding.videosLSTRecyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.videosLSTRecyclerView.adapter = videosAdapter
        binding.videosLSTRecyclerView.addOnScrollListener(object :
            EndlessRecyclerOnScrollListener() {
            override fun onLoadMore() {
                loadMoreVideos()
            }
        })
    }

    private fun loadMoreVideos() {
        videosViewModel.getRecentVideos(
            "json",
            "publish_date:desc",
            10,
            videosAdapter.itemCount
        ).observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { videos ->
                            retrieveList(videos)
                        }
                        Log.d("FFFF", "loadMoreVideos(): getRecentVideos(): SUCCESS")
                    }
                    Status.ERROR -> {
                        Log.d(
                            "FFFF",
                            "loadMoreVideos(): getRecentVideos(): " + it.message.toString()
                        )
                        if (it.message.toString() == "HTTP 502") {
                            //TODO: Check if this doesn't crash the app
                            loadMoreVideos()
                        }
                    }
                    Status.LOADING -> {
                        Log.d("FFFF", "loadMoreVideos(): getRecentVideos(): LOADING")
                    }
                }
            }
        })
    }

    private fun setupObservers() {
        videosViewModel.getRecentVideos("json", "publish_date:desc", 10, 0)
            .observe(viewLifecycleOwner, {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            binding.videosLSTRecyclerView.visibility = View.VISIBLE
                            binding.videosBARProgressBar.visibility = View.GONE
                            resource.data?.let { articles ->
                                retrieveList(articles)
                            }
                            Log.d("FFFF", "getRecentVideos(): SUCCESS")
                        }
                        Status.ERROR -> {
                            binding.videosLSTRecyclerView.visibility = View.VISIBLE
                            binding.videosBARProgressBar.visibility = View.GONE
                            Log.d("FFFF", "getRecentVideos(): " + it.message.toString())
                        }
                        Status.LOADING -> {
                            binding.videosBARProgressBar.visibility = View.VISIBLE
                            binding.videosLSTRecyclerView.visibility = View.GONE
                            Log.d("FFFF", "getRecentVideos(): LOADING")
                        }
                    }
                }
            })
    }

    private fun retrieveList(articleAPI: VideoJsonAPI) {
        videosAdapter.apply {
            addVideos(articleAPI.results as ArrayList<ResultVideo>)
            notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("FFFF", "onDestroyView()")
        Jzvd.releaseAllVideos() // Releasing all videos so they won't keep playing when I'm switching to another fragment.
        _binding = null
    }
}