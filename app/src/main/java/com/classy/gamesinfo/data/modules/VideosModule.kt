package com.classy.gamesinfo.data.modules

import com.classy.gamesinfo.data.repository.VideosRepository
import com.classy.gamesinfo.ui.main.viewmodel.VideosViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val videosModule = module {
    single { VideosRepository(get()) }
    viewModel { VideosViewModel(get()) }
}