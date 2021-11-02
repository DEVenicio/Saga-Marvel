package com.venicio.sagamarvel.di

import com.venicio.sagamarvel.data.db.AppDatabase
import com.venicio.sagamarvel.data.repository.DataSourceLocal
import com.venicio.sagamarvel.data.repository.MovieRepository
import com.venicio.sagamarvel.view.ui.DetailsFragmentArgs
import com.venicio.sagamarvel.viewmodel.SagaMarvelDetailsViewModel
import com.venicio.sagamarvel.viewmodel.SagaMarvelViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {(rp: MovieRepository) -> SagaMarvelViewModel(rp) }
    viewModel { (arguments: DetailsFragmentArgs) -> SagaMarvelDetailsViewModel(arguments) }
}

val repositoryModule = module {
    single { MovieRepository(get()) }
}

val dataSourceModule = module {
    single { DataSourceLocal(get()) }
}

val daoModule = module {
    single { AppDatabase.getDatabase(androidContext()).movieDao }
}