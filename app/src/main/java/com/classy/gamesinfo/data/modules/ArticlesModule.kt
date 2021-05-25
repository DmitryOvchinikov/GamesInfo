package com.classy.gamesinfo.data.modules

import com.classy.gamesinfo.data.repository.ArticlesRepository
import com.classy.gamesinfo.ui.main.viewmodel.ArticlesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val articlesModule = module {
    // a single instance of the ArticlesRepository
    single { ArticlesRepository(get()) }

    viewModel { ArticlesViewModel( get() ) }
}