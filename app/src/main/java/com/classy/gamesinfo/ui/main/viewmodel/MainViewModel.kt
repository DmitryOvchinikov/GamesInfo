package com.classy.gamesinfo.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.classy.gamesinfo.data.repository.MainRepository
import com.classy.gamesinfo.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {
    fun authenticate() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.authenticate()))
        } catch (exception: Exception) {
            emit (Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getAllGames() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getAllGames()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}