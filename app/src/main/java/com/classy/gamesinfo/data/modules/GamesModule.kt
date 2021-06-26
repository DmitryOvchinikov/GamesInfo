package com.classy.gamesinfo.data.modules

import com.classy.gamesinfo.data.repository.GamesRepository
import com.classy.gamesinfo.ui.main.viewmodel.GamesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val gamesModule = module {
    single { GamesRepository( get() ) }
    viewModel { GamesViewModel ( get() ) }
}