package com.classy.gamesinfo.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.classy.gamesinfo.data.repository.ReviewsRepository
import com.classy.gamesinfo.utils.Resource
import kotlinx.coroutines.Dispatchers

class ReviewsViewModel(private val reviewsRepository: ReviewsRepository): ViewModel() {

    fun getRecentReviews(format: String, sort: String, limit: Int, offset: Int) = liveData(
        Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = reviewsRepository.getRecentReviews(format, sort, limit, offset)))
        } catch (exception: Exception) {
            emit (Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}