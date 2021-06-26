package com.classy.gamesinfo.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.classy.gamesinfo.data.repository.GamesRepository
import com.classy.gamesinfo.utils.Resource
import kotlinx.coroutines.Dispatchers

class GamesViewModel(private val gamesRepository: GamesRepository) : ViewModel() {

    fun getGames(format: String, sort: String, limit: Int, offset: Int, filter: String) = liveData(
        Dispatchers.IO
    ) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = gamesRepository.getGames(format, sort, limit, offset, filter)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}