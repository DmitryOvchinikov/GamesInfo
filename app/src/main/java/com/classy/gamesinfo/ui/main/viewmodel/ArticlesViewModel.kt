package com.classy.gamesinfo.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.classy.gamesinfo.data.repository.ArticlesRepository
import com.classy.gamesinfo.ui.main.listeners.EndlessRecyclerOnScrollListener
import com.classy.gamesinfo.utils.Resource
import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener
import kotlinx.coroutines.Dispatchers

class ArticlesViewModel(private val articlesRepository: ArticlesRepository): ViewModel() {

    fun getRecentArticles(format: String, sort: String, limit: Int, offset: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = articlesRepository.getRecentArticles(format, sort, limit, offset)))
        } catch (exception: Exception) {
            emit (Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    }