package com.classy.gamesinfo.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.classy.gamesinfo.data.repository.VideosRepository
import com.classy.gamesinfo.utils.Resource
import kotlinx.coroutines.Dispatchers

class VideosViewModel(private val videosRepository: VideosRepository) : ViewModel() {

    fun getRecentVideos(format: String, sort: String, limit: Int, offset: Int) = liveData(
        Dispatchers.IO
    ) {
        emit(Resource.loading(data = null))
        try {
            emit(
                Resource.success(
                    data = videosRepository.getRecentVideos(
                        format,
                        sort,
                        limit,
                        offset
                    )
                )
            )
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}