package com.classy.gamesinfo.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.classy.gamesinfo.data.repository.ArticlesRepository
import com.classy.gamesinfo.utils.Resource
import kotlinx.coroutines.Dispatchers

class ArticlesViewModel(private val articlesRepository: ArticlesRepository): ViewModel() {

    fun getRecentArticles() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = articlesRepository.getRecentArticles()))
        } catch (exception: Exception) {
            emit (Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}