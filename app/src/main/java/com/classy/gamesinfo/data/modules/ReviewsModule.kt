package com.classy.gamesinfo.data.modules

import com.classy.gamesinfo.data.repository.ReviewsRepository
import com.classy.gamesinfo.ui.main.viewmodel.ReviewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val reviewsModule = module {
    single { ReviewsRepository(get()) }
    viewModel { ReviewsViewModel( get() ) }
}